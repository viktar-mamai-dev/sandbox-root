package com.mamay;

import com.mamay.task7.main.Parser;
import com.mamay.task7.main.SoftService;
import com.mamay.task7.model.BasicSoft;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.SAXException;

@Log4j2
public class Task7Runner {
  private static final String INPUT_FILE = "task7/inputFile.xml";
  private static final String OUTPUT_FILE = "task7/outputFile.xml";

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
