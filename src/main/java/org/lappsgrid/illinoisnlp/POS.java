package org.lappsgrid.illinoisnlp;


import edu.illinois.cs.cogcomp.annotation.AnnotatorException;
import edu.illinois.cs.cogcomp.annotation.BasicTextAnnotationBuilder;
import edu.illinois.cs.cogcomp.core.datastructures.ViewNames;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TokenLabelView;
import edu.illinois.cs.cogcomp.pos.POSAnnotator;
import org.lappsgrid.api.ProcessingService;

import java.util.ArrayList;
import java.util.List;

public class POS implements ProcessingService {


    public POS() {
    }

    @Override
    public String getMetadata() {
        return null;
    }


    @Override
    public String execute(String input) {

        POSAnnotator posAnnotator = new POSAnnotator();
        List<String[]> tokenizedSentences = new ArrayList<>();
        String[] sent1 = {"I", "am", "testing", "this", "on", "a", "Monday", "."};
        tokenizedSentences.add(sent1);
        TextAnnotation ta =
                BasicTextAnnotationBuilder.createTextAnnotationFromTokens(tokenizedSentences);
        try {
            posAnnotator.addView(ta);
        } catch (AnnotatorException e) {
            e.printStackTrace();
            return "Unable to annotate.";
        }

        TokenLabelView view = (TokenLabelView) ta.getView(ViewNames.POS);

        System.out.println(view);

        return null;
    }
}



