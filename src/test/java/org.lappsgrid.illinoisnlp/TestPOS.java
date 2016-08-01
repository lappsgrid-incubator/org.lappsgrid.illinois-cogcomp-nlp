package org.lappsgrid.illinoisnlp;

// JUnit modules for unit tests
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// more APIs for testing code
import org.lappsgrid.api.WebService;
import static org.lappsgrid.discriminator.Discriminators.Uri;
import org.lappsgrid.serialization.Data;
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
        final String testString = "In order to succeed, we must first believe that we can.";

        Data data = new Data(Uri.TEXT, testString);
        String results = this.service.execute(data.asJson());
        System.out.println(results);
    }
}