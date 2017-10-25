package org.opentosca.toscana.core.csar;


import org.eclipse.winery.model.tosca.yaml.TServiceTemplate;
import org.opentosca.toscana.core.parse.CsarParseService;
import org.opentosca.toscana.core.parse.InvalidCsarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class CsarServiceImpl implements CsarService {

    private final static Logger logger = LoggerFactory.getLogger(CsarService.class.getName());

    private final CsarDao csarDao;
    private final CsarParseService csarParser;

    @Autowired
    public CsarServiceImpl(CsarDao dao, CsarParseService parser) {
        this.csarDao = dao;
        this.csarParser = parser;
    }

    @Override
    public Csar submitCsar(String identifier, InputStream csarStream) throws InvalidCsarException {
        Csar csar = csarDao.create(identifier, csarStream);
        try {
            populateWithTemplate(csar);
            return csar;
        } catch (InvalidCsarException e) {
            logger.warn("Failed to submit csar", e);
            // cleanup
            csarDao.delete(csar.getIdentifier());
            throw e;
        }
    }

    private void populateWithTemplate(Csar csar) throws InvalidCsarException {
        if (csar.getTemplate() == null) {
            TServiceTemplate template = csarParser.parse(csar);
            csar.setTemplate(template);
        }
    }

    @Override
    public void deleteCsar(Csar csar) {
        csarDao.delete(csar.getIdentifier());
    }

    @Override
    public List<Csar> getCsars() {
        List<Csar> list = csarDao.findAll();
        for (Csar csar : list) {
            try {
                populateWithTemplate(csar);
            } catch (InvalidCsarException e) {
                logger.error("Encountered invalid csar in repository.", csar, e);
                // TODO wait for discussion outcome, maybe cleanup disk
            }
        }
        return list;
    }

    @Override
    public Csar getCsar(String identifier) {
        Csar csar = csarDao.find(identifier);
        try {
            populateWithTemplate(csar);
        } catch (InvalidCsarException e) {
            logger.error("Encountered invalid csar in repository.", csar, e);
            // TODO wait for discussion outcome, maybe cleanup disk
        }
        return csar;
    }
}