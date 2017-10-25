package org.opentosca.toscana.core.testdata;

import java.io.File;
import java.io.FileNotFoundException;

import org.opentosca.toscana.core.csar.Csar;
import org.opentosca.toscana.core.csar.CsarDao;
import org.opentosca.toscana.core.transformation.Transformation;
import org.opentosca.toscana.core.transformation.TransformationContext;
import org.opentosca.toscana.core.transformation.TransformationDao;
import org.opentosca.toscana.core.transformation.TransformationService;
import org.opentosca.toscana.core.transformation.platform.Platform;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Supplies valid TransformationContext objects for plugin integration tests
 */
public class TestContext {

    @Autowired
    TestCsars testCsars;
    @Autowired
    TransformationService transformationService;
    @Autowired
    CsarDao csarDao;
    @Autowired
    TransformationDao transformationDao;

    /**
     * @param csarFile the csar for the context. To obtain a csar, use TestCsar.`your_csar`
     * @param platform the underlying target platform of the context;
     * @return a valid TransformationContext object based on given csar and platform
     */
    public TransformationContext getContext(File csarFile, Platform platform) throws FileNotFoundException {
        Csar csar = testCsars.getCsar(csarFile);
        transformationService.createTransformation(csar, platform);
        Transformation transformation = csar.getTransformations().get(platform.id);
        File csarContentRoot = csarDao.getContentDir(csar);
        File transformationRoot = transformationDao.getRootDir(transformation);
        TransformationContext context = new TransformationContext(transformation, csarContentRoot, transformationRoot);
        return context;
    }
}