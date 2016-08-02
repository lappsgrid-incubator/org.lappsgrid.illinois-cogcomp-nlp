package org.lappsgrid.illinoisnlp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lappsgrid.api.WebService;

import java.io.IOException;

public class TestTokenSegmenter {
    // this will be the sandbag
    protected WebService service;

    // initiate the service before each test
    @Before
    public void setUp() throws IOException {
        service = new TokenSegmenter();
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
        String testString = "Miss Watson would say, \"Don't put your feet up there, Huckleberry;\" and \"Don't scrunch up like that, Huckleberry—set up straight;\" and pretty soon she would say, \"Don't gap and stretch like that, Huckleberry—why don't you try to behave?\" ";
        String results = this.service.execute(testString);

        System.out.println(results);
    }
}
