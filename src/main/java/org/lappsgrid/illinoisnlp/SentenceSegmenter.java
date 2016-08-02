package org.lappsgrid.illinoisnlp;

import edu.illinois.cs.cogcomp.core.datastructures.IntPair;
import edu.illinois.cs.cogcomp.core.datastructures.Pair;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.nlp.tokenizer.IllinoisTokenizer;
import edu.illinois.cs.cogcomp.nlp.tokenizer.Tokenizer;
import edu.illinois.cs.cogcomp.nlp.utility.TokenizerTextAnnotationBuilder;
import org.lappsgrid.api.ProcessingService;

import java.util.List;

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

        // Set up the TextAnnotationBuilder
        IllinoisTokenizer illinoisTokenizer = new IllinoisTokenizer();
        TokenizerTextAnnotationBuilder taBuilder = new TokenizerTextAnnotationBuilder(illinoisTokenizer);

        // Put input through the taBuilder and get ouput
        TextAnnotation ta = taBuilder.createTextAnnotation(input);
        int numberOfSentences = ta.getNumberOfSentences();
        for (int i = 0; i < numberOfSentences; i++){
            System.out.println(ta.getSentence(i).getText());
        }
        return null;
    }
}
