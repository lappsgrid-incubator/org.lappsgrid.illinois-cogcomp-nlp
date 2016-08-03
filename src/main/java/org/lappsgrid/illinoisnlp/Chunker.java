package org.lappsgrid.illinoisnlp;


import edu.illinois.cs.cogcomp.annotation.AnnotatorException;
import edu.illinois.cs.cogcomp.chunker.main.ChunkerAnnotator;
import edu.illinois.cs.cogcomp.core.datastructures.ViewNames;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.Constituent;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.SpanLabelView;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotation;
import edu.illinois.cs.cogcomp.core.datastructures.textannotation.TextAnnotationUtilities;
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

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Chunker implements ProcessingService{
    public Chunker() {
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

        // the annotators
        POSAnnotator posAnnotator = new POSAnnotator(); // required before chunking
        ChunkerAnnotator chunkerAnnotator = new ChunkerAnnotator();

        // Set up the TextAnnotationBuilder and create a TextAnnotation
        IllinoisTokenizer illinoisTokenizer = new IllinoisTokenizer();
        TokenizerTextAnnotationBuilder taBuilder = new TokenizerTextAnnotationBuilder(illinoisTokenizer);
        TextAnnotation ta = taBuilder.createTextAnnotation(rawText);

        // Annotating with the chunker
        try {
            ta.addView(posAnnotator);
            chunkerAnnotator.addView(ta);
        } catch (AnnotatorException e){
            e.printStackTrace();
            return new Data<>(Discriminators.Uri.ERROR, "Unable to annotate text.").asJson();
        }
        SpanLabelView spanLabelView = (SpanLabelView) ta.getView(ViewNames.SHALLOW_PARSE);

        List<Constituent> nodes = spanLabelView.getConstituents();
        Collections.sort(nodes, TextAnnotationUtilities.constituentStartComparator);
        int numOfNodes = nodes.size();
        for (int i = 0; i < numOfNodes; i++){ // for each chunk (node)
            Constituent node = nodes.get(i);

            int start = node.getStartCharOffset();
            int end = node.getEndCharOffset();

            Annotation a = new Annotation("chunk" + i, "Chunk", start, end);
            a.setAtType(Discriminators.Uri.CHUNK);
            a.addFeature(Discriminators.Uri.TEXT, node.getTokenizedSurfaceForm());
            a.addFeature("chunk type", node.getLabel());
            resultsView.add(a);
        }

        resultsView.addContains(Discriminators.Uri.CHUNK, this.getClass().getName(), "chunks");

        Container resultsContainer= new Container();
        resultsContainer.setText(container.getText());
        resultsContainer.addView(resultsView);
        data = new DataContainer(resultsContainer);

        return data.asPrettyJson();
    }
}
