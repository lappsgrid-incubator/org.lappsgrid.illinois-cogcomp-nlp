package org.lappsgrid.illinoisnlp;

import edu.illinois.cs.cogcomp.annotation.AnnotatorException;
import edu.illinois.cs.cogcomp.core.datastructures.ViewNames;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.Constituent;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.SpanLabelView;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TokenLabelView;
import edu.illinois.cs.cogcomp.ner.NERAnnotator;
import edu.illinois.cs.cogcomp.nlp.tokenizer.IllinoisTokenizer;
import edu.illinois.cs.cogcomp.nlp.utility.TokenizerTextAnnotationBuilder;
import edu.illinois.cs.cogcomp.pos.POSAnnotator;
import org.apache.axis.Version;
import org.lappsgrid.api.ProcessingService;
import org.lappsgrid.discriminator.Discriminators;
import org.lappsgrid.metadata.IOSpecification;
import org.lappsgrid.metadata.ServiceMetadata;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.DataContainer;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.serialization.lif.Annotation;
import org.lappsgrid.serialization.lif.Container;
import org.lappsgrid.serialization.lif.View;
import org.lappsgrid.vocabulary.Features;

import java.io.IOException;
import java.util.List;

public class NamedEntityRecognizer implements ProcessingService {

    private NERAnnotator nerAnnotator;
    private String metadata;

    public NamedEntityRecognizer() throws IOException{
        this.nerAnnotator = new NERAnnotator(ViewNames.NER_ONTONOTES);

        ServiceMetadata md = new ServiceMetadata();

        md.setName(this.getClass().getName());
        md.setAllow(Discriminators.Uri.ANY);
        md.setDescription("UIUC Part of Speech Tagger");
        md.setVendor("http://www.lappsgrid.org");
        md.setLicense(Discriminators.Uri.APACHE2);

        IOSpecification requires = new IOSpecification();
        requires.addFormat(Discriminators.Uri.TEXT);
        requires.addLanguage("en");

        IOSpecification produces = new IOSpecification();
        produces.addFormat(Discriminators.Uri.LAPPS);
        produces.addLanguage("en");

        md.setRequires(requires);
        md.setProduces(produces);

        Data<ServiceMetadata> data = new Data<>(Discriminators.Uri.META, md);
        metadata = data.asPrettyJson();
    }

    @Override
    public String getMetadata() {
        return metadata;
    }



    @Override
    public String execute(String input) {
        // Parse the input.
        Data data = Serializer.parse(input, Data.class);

        //  Check the discriminator
        final String discriminator = data.getDiscriminator();
        if (discriminator.equals(Discriminators.Uri.ERROR)) {
            // Return the input unchanged.
            return input;
        }

        // Extract the data.
        Container container;
        String rawText;
        if (discriminator.equals(Discriminators.Uri.TEXT)){
            rawText = data.getPayload().toString();
            container = new Container();
            container.setText(rawText);
        } else {
            // This is a format we don't accept.
            String message = String.format("Unsupported discriminator type: %s", discriminator);
            return new Data<>(Discriminators.Uri.ERROR, message).asJson();
        }


        View resultsView = container.newView();

        // Set up the TextAnnotationBuilder and create a TextAnnotation
        IllinoisTokenizer illinoisTokenizer = new IllinoisTokenizer();
        TokenizerTextAnnotationBuilder taBuilder = new TokenizerTextAnnotationBuilder(illinoisTokenizer);
        TextAnnotation ta = taBuilder.createTextAnnotation(rawText);

        // annotate
        try {
            ta.addView(new POSAnnotator());
        } catch (AnnotatorException e){
            e.printStackTrace();
            return null;
        }
        nerAnnotator.addView(ta);


        SpanLabelView labelView = (SpanLabelView) ta.getView(ViewNames.NER_ONTONOTES);
        List<Constituent> nodes = labelView.getConstituents();
        int numNodes = nodes.size();
        for (int i = 0; i < numNodes; i++){
            Constituent node = nodes.get(i);
            String nodeString = node.getTokenizedSurfaceForm();

            int start = node.getStartCharOffset();
            int end = node.getEndCharOffset() - 1;

            Annotation a = new Annotation("namedentity" + i, "Named Entity", start, end);
            a.setAtType(Discriminators.Uri.ANNOTATION);
            // a.addFeature(Discriminators.Uri.MARKABLE, nodeString);
            a.addFeature(Features.NamedEntity.CATEGORY, node.getLabel());
            resultsView.add(a);
        }

        resultsView.addContains(Discriminators.Uri.POS, this.getClass().getName(), "named-entities:uiuc");

        data = new DataContainer(container);

        return data.asPrettyJson();
    }
}



