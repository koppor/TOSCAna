package org.opentosca.toscana.cli;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class MainTest {
    private CliController main = null;

    @Before
    public void setUp(){
        main = new CliController();
    }

    @Test
    public void testRunningCli(){
        assertTrue(main.createCli(new String[] {""}));
    }

}

