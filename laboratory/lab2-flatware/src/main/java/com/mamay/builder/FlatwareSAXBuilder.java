package com.mamay.builder;

import com.mamay.entity.Flatware;
import com.mamay.exception.LogicalException;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@Log4j2
public class FlatwareSAXBuilder extends AbstractFlatwareBuilder {

    private FlatwareHandler handler;
    private SAXParser reader;

    public FlatwareSAXBuilder() {
        handler = new FlatwareHandler();
        try {
            reader = SAXParserFactory.newInstance().newSAXParser();
        } catch (SAXException | ParserConfigurationException e) {
            log.error(e);
        }
    }

    public void buildFlatwares(String fileName) throws LogicalException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new LogicalException("Such file does not exist!");
        }
        try {
            reader.parse(fileName, handler);
        } catch (SAXException | IOException e) {
            log.error(e);
        }
        flatwares = handler.getFlatwares();
    }
}
