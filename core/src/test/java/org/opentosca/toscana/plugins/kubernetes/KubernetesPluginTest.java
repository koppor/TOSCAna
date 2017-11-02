package org.opentosca.toscana.plugins.kubernetes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.opentosca.toscana.core.BaseSpringTest;
import org.opentosca.toscana.core.csar.Csar;
import org.opentosca.toscana.core.parse.CsarParseService;
import org.opentosca.toscana.core.plugin.PluginFileAccess;
import org.opentosca.toscana.core.testdata.TestCsars;
import org.opentosca.toscana.core.transformation.TransformationContext;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.opentosca.toscana.core.testdata.TestCsars.CSAR_YAML_VALID_DOCKER_SIMPLETASK;

public class KubernetesPluginTest extends BaseSpringTest {
    private static final Logger log = LoggerFactory.getLogger(KubernetesPluginTest.class);

    private static KubernetesPlugin plugin;
    @Autowired
    TestCsars testCsars;

    @Autowired
    CsarParseService csarParseService;

    @Before
    public void setUp() {
        plugin = new KubernetesPlugin();
    }

    @Test
    public void transformationMockTest() throws Exception {
        Csar csar = testCsars.getCsar(CSAR_YAML_VALID_DOCKER_SIMPLETASK);
        TransformationContext context = mock(TransformationContext.class);
        PluginFileAccess pluginFileAccess = mock(PluginFileAccess.class);
        when(context.getPluginFileAccess()).thenReturn(pluginFileAccess);
        when(context.getServiceTemplate()).thenReturn(csarParseService.parse(csar));
        when(pluginFileAccess.access(any(String.class))).thenReturn(new BufferedWriter(new FileWriter(new File(tmpdir, "blob.blob"))));
        plugin.transform(context);
        verify(pluginFileAccess).access("/Readme.md");
        verify(pluginFileAccess).access("/simple-task-app_resource.yaml");
        List<String> expectedDockerPathFiles = Lists.newArrayList("index.php", "mysql-credentials.php", "createdb.sql", "Dockerfile");
        for (String s : expectedDockerPathFiles) {
            verify(pluginFileAccess).copy("simple-task-app/" + s);
        }
    }
}
