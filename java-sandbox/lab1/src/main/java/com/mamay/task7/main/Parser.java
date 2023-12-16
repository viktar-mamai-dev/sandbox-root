package com.mamay.task7.main;

import com.mamay.task7.model.BasicSoft;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Parser {

  private final DocumentBuilder builder;
  private final Transformer transformer;

  public Parser() {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      builder = factory.newDocumentBuilder();

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      transformer = transformerFactory.newTransformer();
    } catch (ParserConfigurationException | TransformerConfigurationException e) {
      throw new RuntimeException("Error during initialization");
    }
  }

  public List<BasicSoft> unmarshallWithDOM(String file)
      throws ParserConfigurationException, SAXException, IOException {

    Document document = builder.parse(returnFile(file));
    List<BasicSoft> programs = new ArrayList<BasicSoft>();

    NodeList nodeList = document.getDocumentElement().getChildNodes();
    for (int i = 0; i < nodeList.getLength(); i++) {
      Node node = nodeList.item(i);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element elem = (Element) node;

        String name = extractValue(elem, "name");
        double price = Double.parseDouble(extractValue(elem, "price"));
        double size = Double.parseDouble(extractValue(elem, "size"));
        programs.add(new BasicSoft(name, price, size));
      }
    }
    return programs;
  }

  private String extractValue(Element elem, String name) {
    return elem.getElementsByTagName(name).item(0).getChildNodes().item(0).getNodeValue();
  }

  public void writeToXML(double totalPrice, double totalSize, String file)
      throws TransformerException, ParserConfigurationException {
    Document document = builder.newDocument();
    Element rootElement = document.createElement("programs");
    document.appendChild(rootElement);

    Element price = document.createElement("totalPrice");
    Text textPrice = document.createTextNode(String.valueOf(totalPrice));
    price.appendChild(textPrice);
    rootElement.appendChild(price);

    Element size = document.createElement("totalSize");
    Text textElemSize = document.createTextNode(String.valueOf(totalSize));
    size.appendChild(textElemSize);
    rootElement.appendChild(size);

    // creating and writing to xml file
    DOMSource domSource = new DOMSource(document);
    StreamResult streamResult = new StreamResult(returnFile(file));
    transformer.transform(domSource, streamResult);
  }

  private File returnFile(String fileName) {
    URL resource = getClass().getClassLoader().getResource(fileName);
    if (resource == null) {
      throw new IllegalArgumentException("File not found");
    } else {
      return new File(resource.getFile());
    }
  }
}
