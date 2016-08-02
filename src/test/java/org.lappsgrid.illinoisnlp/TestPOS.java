package org.lappsgrid.illinoisnlp;

// JUnit modules for unit tests
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// more APIs for testing code
import org.lappsgrid.api.WebService;
import static org.lappsgrid.discriminator.Discriminators.Uri;

import org.lappsgrid.discriminator.Discriminators;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.DataContainer;
import org.lappsgrid.serialization.lif.Annotation;
import org.lappsgrid.serialization.lif.Container;
import org.lappsgrid.serialization.lif.View;
import org.lappsgrid.vocabulary.Features;

import java.io.IOException;

public class TestPOS {

    // this will be the sandbag
    protected WebService service;

    // initiate the service before each test
    @Before
    public void setUp() throws IOException {
        service = new POS();
    }

    // then destroy it after the test
    @After
    public void tearDown() {
        service = null;
    }

    @Test
    public void testMetadata() {  }

    @Test
    public void testExecute() {
        // Entering the tokens for "Don't count the days. Make the days count." into a Data object
        Container container = new Container();
        container.setText("Don't count the days. Make the days count.");
        View view = container.newView();
        Annotation a;

        a = view.newAnnotation("tok0", Discriminators.Uri.TOKEN, 0, 2);
        a.addFeature(Features.Token.WORD, "Don");
        a = view.newAnnotation("tok1", Discriminators.Uri.TOKEN, 3, 4);
        a.addFeature(Features.Token.WORD, "'t");
        a = view.newAnnotation("tok2", Discriminators.Uri.TOKEN, 6, 10);
        a.addFeature(Features.Token.WORD, "count");
        a = view.newAnnotation("tok3", Discriminators.Uri.TOKEN, 12, 14);
        a.addFeature(Features.Token.WORD, "the");
        a = view.newAnnotation("tok0", Discriminators.Uri.TOKEN, 16, 19);
        a.addFeature(Features.Token.WORD, "days");
        a = view.newAnnotation("tok0", Discriminators.Uri.TOKEN, 20, 20);
        a.addFeature(Features.Token.WORD, ".");
        a = view.newAnnotation("tok0", Discriminators.Uri.TOKEN, 22, 25);
        a.addFeature(Features.Token.WORD, "Make");
        a = view.newAnnotation("tok0", Discriminators.Uri.TOKEN, 27, 29);
        a.addFeature(Features.Token.WORD, "the");
        a = view.newAnnotation("tok0", Discriminators.Uri.TOKEN, 31, 34);
        a.addFeature(Features.Token.WORD, "days");
        a = view.newAnnotation("tok0", Discriminators.Uri.TOKEN, 36, 40);
        a.addFeature(Features.Token.WORD, "count");
        a = view.newAnnotation("tok0", Discriminators.Uri.TOKEN, 41, 41);
        a.addFeature(Features.Token.WORD, ".");

        Data data = new DataContainer(container);
        data.setDiscriminator(Discriminators.Uri.TOKEN);

        // add parameters
        data.setParameter("model", this.getClass().getResource("/masc_500k_texts_word_by_word.model"));

        // call `execute()` with jsonized input,
        String string = this.service.execute(data.asJson());

        System.out.println(string);
    }
}