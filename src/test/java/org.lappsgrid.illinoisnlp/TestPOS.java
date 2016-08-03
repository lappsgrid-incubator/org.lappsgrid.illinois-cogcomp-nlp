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
        String testString = "Don't count the days. Make the days count.";
        Data data = new Data(Uri.TEXT, testString);

        // call `execute()` with jsonized input,
        String string = this.service.execute(data.asJson());

        System.out.println(string);
    }
}