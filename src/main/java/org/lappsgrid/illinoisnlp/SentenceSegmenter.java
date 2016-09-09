package org.lappsgrid.illinoisnlp;

import edu.illinois.cs.cogcomp.core.datastructures.textannotation.Sentence;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.nlp.tokenizer.IllinoisTokenizer;
import edu.illinois.cs.cogcomp.nlp.utility.TokenizerTextAnnotationBuilder;
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

import java.util.Map;


public class SentenceSegmenter implements ProcessingService{

    private String metadata;

    public SentenceSegmenter() {
        ServiceMetadata md = new ServiceMetadata();

        md.setName(this.getClass().getName());
        md.setAllow(Discriminators.Uri.ANY);
        md.setDescription("UIUC Sentence Segmenter");
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
        // Step #1: Parse the input.
        Data data = Serializer.parse(input, Data.class);

        // Step #2: Check the discriminator
        final String discriminator = data.getDiscriminator();
        if (discriminator.equals(Discriminators.Uri.ERROR)) {
            // Return the input unchanged.
            return input;
        }

        // Step #3: Extract the text.
        Container container;
        if (discriminator.equals(Discriminators.Uri.TEXT)) {
            container = new Container();
            container.setText(data.getPayload().toString());
        } else if (discriminator.equals(Discriminators.Uri.LAPPS)) {
            container = new Container((Map) data.getPayload());
        } else {
            // This is a format we don't accept.
            String message = String.format("Unsupported discriminator type: %s", discriminator);
            return new Data<>(Discriminators.Uri.ERROR, message).asJson();
        }
        String rawText = container.getText();

        View resultsView = container.newView();

        // Set up the TextAnnotationBuilder
        IllinoisTokenizer illinoisTokenizer = new IllinoisTokenizer();
        TokenizerTextAnnotationBuilder taBuilder = new TokenizerTextAnnotationBuilder(illinoisTokenizer);

        // Put input through the taBuilder and get ouput
        TextAnnotation ta = taBuilder.createTextAnnotation(rawText);
        int numberOfSentences = ta.getNumberOfSentences();
        for (int i = 0; i < numberOfSentences; i++){
            Sentence sentence = ta.getSentence(i);
            int start = sentence.getStartSpan();
            int end = sentence.getEndSpan();
            Annotation a = new Annotation("sentence" + i, "Sentence" ,start, end);
            a.setAtType(Discriminators.Uri.SENTENCE);
            // a.addFeature(Discriminators.Uri.TEXT, sentence.getText());
            resultsView.add(a);
        }

        resultsView.addContains(Discriminators.Uri.SENTENCE, this.getClass().getName(), "sentence:uiuc");

        data = new DataContainer(container);

        return data.asPrettyJson();
    }
}
