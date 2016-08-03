package org.lappsgrid.illinoisnlp;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lappsgrid.api.WebService;
import org.lappsgrid.discriminator.Discriminators;
import org.lappsgrid.serialization.Data;

import java.io.IOException;

public class TestChunker {

    // this will be the sandbag
    protected WebService service;

    // initiate the service before each test
    @Before
    public void setUp() throws IOException {
        service = new Chunker();
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
        String testString = "So I wilted right down on to the planks then, and give up; and it was all I could do to keep from crying.";
        Data data = new Data(Discriminators.Uri.TEXT, testString);
        String results = this.service.execute(data.asJson());

        System.out.println(results);
    }
}
