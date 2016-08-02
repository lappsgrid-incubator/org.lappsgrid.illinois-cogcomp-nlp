package org.lappsgrid.illinoisnlp;

import edu.illinois.cs.cogcomp.core.datastructures.IntPair;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.nlp.tokenizer.IllinoisTokenizer;
import edu.illinois.cs.cogcomp.nlp.utility.TokenizerTextAnnotationBuilder;
import org.lappsgrid.api.ProcessingService;

public class TokenSegmenter implements ProcessingService {
    public TokenSegmenter() {
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
        String[] tokens = ta.getTokens();
        int numOfTokens = tokens.length;
        for (int i = 0; i < numOfTokens; i++) {
            String token = tokens[i];
            IntPair offsets = ta.getTokenCharacterOffset(i);
            int start = offsets.getFirst();
            int end = offsets.getSecond() - 1;
            System.out.println(token + ' ' + start + ' ' + end);
        }
        return null;
    }
}
