package org.lappsgrid.illinoisnlp;

import edu.illinois.cs.cogcomp.core.datastructures.IntPair;
import edu.illinois.cs.cogcomp.core.datastructures.Pair;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.Sentence;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.nlp.tokenizer.IllinoisTokenizer;
import edu.illinois.cs.cogcomp.nlp.tokenizer.Tokenizer;
import edu.illinois.cs.cogcomp.nlp.utility.TokenizerTextAnnotationBuilder;
import org.lappsgrid.api.ProcessingService;
import org.lappsgrid.discriminator.Discriminators;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.DataContainer;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.serialization.lif.Annotation;
import org.lappsgrid.serialization.lif.Container;
import org.lappsgrid.serialization.lif.View;
import org.lappsgrid.vocabulary.Features;

import java.util.List;
import java.util.Map;

/**
 * Created by Baian on 8/1/2016.
 */
public class SentenceSegmenter implements ProcessingService{
    public SentenceSegmenter() {
    }

    @Override
    public String getMetadata() {
        return null;
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
            Annotation a = new Annotation(Discriminators.Uri.SENTENCE, start, end);
            a.addFeature("sentence", sentence.getText());
            resultsView.add(a);
        }

        resultsView.addContains(Discriminators.Uri.SENTENCE, this.getClass().getName(), "sentences");

        Container resultsContainer= new Container();
        resultsContainer.setText(container.getText());
        resultsContainer.addView(resultsView);
        data = new DataContainer(resultsContainer);

        return data.asPrettyJson();
    }
}
