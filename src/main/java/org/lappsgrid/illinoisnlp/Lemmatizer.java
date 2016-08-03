package org.lappsgrid.illinoisnlp;

import edu.illinois.cs.cogcomp.annotation.AnnotatorException;
import edu.illinois.cs.cogcomp.core.datastructures.ViewNames;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.Constituent;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TokenLabelView;
import edu.illinois.cs.cogcomp.nlp.lemmatizer.IllinoisLemmatizer;
import edu.illinois.cs.cogcomp.nlp.tokenizer.IllinoisTokenizer;
import edu.illinois.cs.cogcomp.nlp.utility.TokenizerTextAnnotationBuilder;
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

import java.util.List;


public class Lemmatizer implements ProcessingService {


    public Lemmatizer() {
    }

    @Override
    public String getMetadata() {
        return null;
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
        POSAnnotator posAnnotator = new POSAnnotator();
        IllinoisLemmatizer illinoisLemmatizer = new IllinoisLemmatizer();
        try {
            posAnnotator.addView(ta);
        } catch (AnnotatorException e) {
            e.printStackTrace();
            return "Unable to annotate part of speech.";
        }
        try {
            illinoisLemmatizer.addView(ta);
        } catch (AnnotatorException e) {
            e.printStackTrace();
            return "Unable to annotate lemmas.";
        }


        TokenLabelView labelView = (TokenLabelView) ta.getView(ViewNames.LEMMA);
        List<Constituent> tokens = labelView.getConstituents();
        int numTokens = tokens.size();
        for (int i = 0; i < numTokens; i++){
            Constituent token = tokens.get(i);
            String tokenString = token.getTokenizedSurfaceForm();

            int start = token.getStartCharOffset();
            int end = token.getEndCharOffset() - 1;

            Annotation a = new Annotation("tok" + i, "token", start, end);

            a.setAtType(Discriminators.Uri.LEMMA);
            a.addFeature(Discriminators.Uri.TOKEN, tokenString);
            a.addFeature(Features.Token.LEMMA, token.getLabel());
            resultsView.add(a);
        }

        resultsView.addContains(Discriminators.Uri.LEMMA, this.getClass().getName(), "lemma:uiuc");

        data = new DataContainer(container);

        return data.asPrettyJson();
    }
}
