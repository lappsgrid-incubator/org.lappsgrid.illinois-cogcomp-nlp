package org.lappsgrid.illinoisnlp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lappsgrid.api.WebService;
import org.lappsgrid.discriminator.Discriminators;
import org.lappsgrid.metadata.IOSpecification;
import org.lappsgrid.metadata.ServiceMetadata;
import org.lappsgrid.serialization.Data;
import org.lappsgrid.serialization.Serializer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

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
        String json = service.getMetadata();
        assertNotNull("service.getMetadata() returned null", json);

        Data data = Serializer.parse(json, Data.class);
        assertNotNull("Unable to parse metadata json.", data);
        assertNotSame(data.getPayload().toString(), Discriminators.Uri.ERROR, data.getDiscriminator());

        ServiceMetadata metadata = new ServiceMetadata((Map) data.getPayload());
        IOSpecification produces = metadata.getProduces();
        IOSpecification requires = metadata.getRequires();

        assertEquals("Name is not correct", TokenSegmenter.class.getName(), metadata.getName());
        assertEquals("\"allow\" field not equal", Discriminators.Uri.ANY, metadata.getAllow());
        assertEquals("License not correct", Discriminators.Uri.APACHE2, metadata.getLicense());

        List<String> requiresList = requires.getFormat();
        assertTrue("Text not accepted", requiresList.contains(Discriminators.Uri.TEXT));
        assertTrue("Required languages do not contain English", requires.getLanguage().contains("en"));

        List<String> producesList = produces.getFormat();
        assertTrue("Tool does not produce LAPPSGRID format", producesList.contains(Discriminators.Uri.LAPPS));
    }

    @Test
    public void testExecute() {
        String testString = "Miss Watson would say, \"Don't put your feet up there, Huckleberry;\" and \"Don't scrunch up like that, Huckleberry—set up straight;\" and pretty soon she would say, \"Don't gap and stretch like that, Huckleberry—why don't you try to behave?\" ";
        Data data = new Data(Discriminators.Uri.TEXT, testString);

        String results = this.service.execute(data.asJson());

        System.out.println(results);
    }
}
