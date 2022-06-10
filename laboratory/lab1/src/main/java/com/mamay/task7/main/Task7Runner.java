package com.mamay.task7.main;

import com.mamay.task7.model.BasicSoft;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Log4j2
public class Task7Runner {
    private final static String INPUT_FILE = "task7/inputFile.xml";
    private final static String OUTPUT_FILE = "task7/outputFile.xml";

    public static void main(String[] args) {
        Parser parser = new Parser();
        SoftService calc = new SoftService();
        try {
            List<BasicSoft> programs = parser.unmarshallWithDOM(INPUT_FILE);
            log.debug(programs);
            double totalPrice = calc.calculateTotalPrice(programs);
            double totalSize = calc.calculateTotalSize(programs);
            parser.writeToXML(totalPrice, totalSize, OUTPUT_FILE);
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
