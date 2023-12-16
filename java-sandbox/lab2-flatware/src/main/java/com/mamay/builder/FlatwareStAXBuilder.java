package com.mamay.builder;

import com.mamay.entity.Flatware;
import com.mamay.entity.PrimaryFlatware;
import com.mamay.entity.SecondaryFlatware;
import com.mamay.exception.LogicalException;
import com.mamay.type.BladeType;
import com.mamay.type.FlatwareType;
import com.mamay.type.HelveType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FlatwareStAXBuilder extends AbstractFlatwareBuilder {
  private XMLInputFactory inputFactory;

  public FlatwareStAXBuilder() {
    inputFactory = XMLInputFactory.newInstance();
  }

  public FlatwareStAXBuilder(Set<Flatware> flatwares) {
    super(flatwares);
    inputFactory = XMLInputFactory.newInstance();
  }

  public void buildFlatwares(String fileName) throws LogicalException {
    File file = new File(fileName);
    if (!file.exists()) {
      throw new LogicalException("Such file does not exist!");
    }
    try (FileInputStream inputStream = new FileInputStream(file)) {
      XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
      while (reader.hasNext()) {
        int type = reader.next();
        if (type == XMLStreamConstants.START_ELEMENT) {
          String name = reader.getLocalName();
          if ("primary-flatware".equals(name)) {
            Flatware st = buildPrimaryFlatware(reader);
            flatwares.add(st);
          } else if ("secondary-flatware".equals(name)) {
            Flatware st = buildSecondaryFlatware(reader);
            flatwares.add(st);
          }
        }
      }
    } catch (XMLStreamException | IOException | ParseException ex) {
      log.error(ex);
    }
  }

  private PrimaryFlatware buildPrimaryFlatware(XMLStreamReader reader) throws XMLStreamException {
    PrimaryFlatware fw = new PrimaryFlatware();
    fw.setId(reader.getAttributeValue(null, "id"));
    fw = (PrimaryFlatware) buildFlatware(reader, fw);
    return fw;
  }

  private SecondaryFlatware buildSecondaryFlatware(XMLStreamReader reader)
      throws XMLStreamException, ParseException {
    SecondaryFlatware fw = new SecondaryFlatware();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    Date production = format.parse(reader.getAttributeValue(null, "production-date"));
    fw.setProductionDate(production);
    String durability = reader.getAttributeValue(null, "durability");
    if (durability != null) {
      fw.setDurability(Integer.parseInt(durability));
    }
    fw = (SecondaryFlatware) buildFlatware(reader, fw);
    return fw;
  }

  private Flatware buildFlatware(XMLStreamReader reader, Flatware fw) throws XMLStreamException {
    fw.setOrigin(reader.getAttributeValue(null, "origin"));
    boolean value = Boolean.parseBoolean(reader.getAttributeValue(null, "value"));
    fw.setValue(value);
    String name;
    while (reader.hasNext()) {
      int type = reader.next();
      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          name = reader.getLocalName();
          switch (name.toLowerCase()) {
            case "type" -> {
              String ftype = getXMLText(reader);
              fw.setFlatware(FlatwareType.valueOf(ftype.toUpperCase()));
            }
            case "price" -> {
              int price = Integer.parseInt(getXMLText(reader));
              ((PrimaryFlatware) fw).setPrice(price);
            }
            case "visual" -> fw.setVisual(getXMLVisual(reader));
            case "dish" -> ((SecondaryFlatware) fw).addDish(getXMLText(reader));
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          name = reader.getLocalName();
          if ("primary-flatware".equals(name) || "secondary-flatware".equals(name)) {
            return fw;
          }
          break;
      }
    }
    throw new XMLStreamException("Unknown element in tag Flatware");
  }

  private Flatware.Visual getXMLVisual(XMLStreamReader reader) throws XMLStreamException {
    Flatware.Visual visual = new Flatware.Visual();
    while (reader.hasNext()) {
      int type = reader.next();
      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          String name = reader.getLocalName();
          String text = getXMLText(reader);
          switch (name) {
            case "blade-length":
              Integer blength = Integer.parseInt(text);
              visual.setBladeLength(blength);
              break;
            case "blade-width":
              Integer bwidth = Integer.parseInt(text);
              visual.setBladeWidth(bwidth);
              break;
            case "prong-length":
              Integer plength = Integer.parseInt(text);
              visual.setProngLength(plength);
              break;
            case "blade-material":
              BladeType btype = BladeType.valueOf(text.toUpperCase());
              visual.setBlade(btype);
              break;
            case "helve":
              HelveType htype = HelveType.valueOf(text.toUpperCase());
              visual.setHelve(htype);
              break;
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          name = reader.getLocalName();
          if ("visual".equals(name)) {
            return visual;
          }
          break;
      }
    }
    throw new XMLStreamException("Unknown element in tag Visual");
  }

  private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
    String text = null;
    if (reader.hasNext()) {
      reader.next();
      text = reader.getText();
    }
    return text.trim();
  }
}
