package org.lappsgrid.illinoisnlp;

import edu.illinois.cs.cogcomp.core.datastructures.IntPair;
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
import org.lappsgrid.vocabulary.Features;

import java.util.Map;

public class TokenSegmenter implements ProcessingService {

    private String metadata;

    public TokenSegmenter() {
        ServiceMetadata md = new ServiceMetadata();

        md.setName(this.getClass().getName());
        md.setAllow(Discriminators.Uri.ANY);
        md.setDescription("UIUC Token Segmenter");
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
        String[] tokens = ta.getTokens();
        int numOfTokens = tokens.length;
        for (int i = 0; i < numOfTokens; i++) {
            String token = tokens[i];
            IntPair offsets = ta.getTokenCharacterOffset(i);
            int start = offsets.getFirst();
            int end = offsets.getSecond() - 1;
            Annotation a = new Annotation("tok" + i , Discriminators.Uri.TOKEN, start, end);
            a.setAtType(Discriminators.Uri.TOKEN);
            a.addFeature(Features.Token.WORD, token);
            resultsView.add(a);
        }

        resultsView.addContains(Discriminators.Uri.TOKEN, this.getClass().getName(), "tokens");


        data = new DataContainer(container);

        return data.asPrettyJson();
    }
}
