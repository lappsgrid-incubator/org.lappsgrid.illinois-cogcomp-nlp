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

public class TestSentenceSegmenter {

    // this will be the sandbag
    protected WebService service;

    // initiate the service before each test
    @Before
    public void setUp() throws IOException {
        service = new SentenceSegmenter();
    }

    // then destroy it after the test
    @After
    public void tearDown() {
        service = null;
    }

    @Test
    public void testMetadata() {
    }

    @Test
    public void testExecute() {
        String testString = "A fearful man, all in coarse gray, with a great iron on his leg. A man with no hat, and with broken shoes, and with an old rag tied round his head. A man who had been soaked in water, and smothered in mud, and lamed by stones, and cut by flints, and stung by nettles, and torn by briars; who limped, and shivered, and glared, and growled; and whose teeth chattered in his head as he seized me by the chin.\n" +
                "\"Oh! Don't cut my throat, sir,\" I pleaded in terror. \"Pray don't do it, sir.\"\n" +
                "\"Tell us your name!\" said the man. \"Quick!\"\n" +
                "\"Pip, sir.\"\n" +
                "\"Once more,\" said the man, staring at me. \"Give it mouth!\"\n" +
                "\"Pip. Pip, sir.\"\n" +
                "\"Show us where you live,\" said the man. \"Pint out the place!\"";

        Data data = new Data(Uri.TEXT, testString);
        String results = this.service.execute(data.asJson());

        System.out.println(results);
    }
}