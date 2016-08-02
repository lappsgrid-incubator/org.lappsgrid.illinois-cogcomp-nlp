package org.lappsgrid.illinoisnlp;


import edu.illinois.cs.cogcomp.annotation.AnnotatorException;
import edu.illinois.cs.cogcomp.annotation.BasicTextAnnotationBuilder;
import edu.illinois.cs.cogcomp.core.datastructures.ViewNames;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TokenLabelView;
import edu.illinois.cs.cogcomp.pos.POSAnnotator;
import org.lappsgrid.api.ProcessingService;
import org.lappsgrid.discriminator.Discriminators;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.DataContainer;
import org.lappsgrid.serialization.Serializer;
import org.lappsgrid.serialization.lif.Annotation;
import org.lappsgrid.serialization.lif.Container;
import org.lappsgrid.serialization.lif.View;
import org.lappsgrid.vocabulary.Features;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class POS implements ProcessingService {


    public POS() {
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

        // Step #3: Extract the data.
        Container container;
        if (discriminator.equals(Discriminators.Uri.TOKEN) ||
                discriminator.equals(Discriminators.Uri.LAPPS)) {
            container = new Container((Map) data.getPayload());
        } else {
            // This is a format we don't accept.
            String message = String.format("Unsupported discriminator type: %s", discriminator);
            return new Data<>(Discriminators.Uri.ERROR, message).asJson();
        }

        // Get the tokens from the input and turn them into Mallet's desired format
        View view = new View(container.getView(0));
        List<Annotation> annotations = view.getAnnotations();
        int numTokens = annotations.size();

        String[] tokens = new String[numTokens];
        for (int i = 0; i < numTokens; i++) {
            String token = annotations.get(i).getFeature(Features.Token.WORD);
            if (token != null) {
                tokens[i] = token;
            } else {
                return new Data<>(Discriminators.Uri.ERROR, "Found non-tokens.").asJson();
            }
        }

        POSAnnotator posAnnotator = new POSAnnotator();
        List<String[]> tokenizedSentences = new ArrayList<>();

        tokenizedSentences.add(tokens);

        TextAnnotation ta =
                BasicTextAnnotationBuilder.createTextAnnotationFromTokens(tokenizedSentences);
        try {
            posAnnotator.addView(ta);
        } catch (AnnotatorException e) {
            e.printStackTrace();
            return "Unable to annotate.";
        }

        TokenLabelView labelView = (TokenLabelView) ta.getView(ViewNames.POS);
        View resultsView = new View();
        for (int i = 0; i < labelView.getTextAnnotation().size(); i++){
            Annotation a = annotations.get(i);
            a.addFeature(Features.Token.POS, labelView.getLabel(i));
            resultsView.add(a);
        }

        resultsView.addContains(Discriminators.Uri.POS, this.getClass().getName(), "part of speech");

        Container resultsContainer= new Container();
        resultsContainer.setText(container.getText());
        resultsContainer.addView(resultsView);
        data = new DataContainer(resultsContainer);

        return data.asPrettyJson();
    }
}



