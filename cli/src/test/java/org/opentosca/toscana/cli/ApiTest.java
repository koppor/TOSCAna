package org.opentosca.toscana.cli;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ApiTest {
    private ApiController api = null;

    @Before
    public void setUp(){
        api = new ApiController();
    }

    @Test
    public void runTransformation(){
        assertEquals("Starting Transformation",api.startTransformation());
    }

    @Test
    public void abortTransformation(){
        api.startTransformation();
        assertEquals("Aborting Transformation",api.abortTransformation());
    }

    @Test
    public void testVerbose(){
        assertEquals("Logs activated",api.verbose());
    }

    @Test
    public void testListing(){
        assertEquals("Show available Platforms",api.listPlatforms());
    }

    @Test
    public void testSelectPlatform(){
        assertEquals("Platform selected: testunit",api.selectPlatform("testunit"));
    }

}

