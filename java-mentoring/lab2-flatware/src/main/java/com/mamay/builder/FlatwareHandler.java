package com.mamay.builder;

import com.mamay.entity.Flatware;
import com.mamay.entity.PrimaryFlatware;
import com.mamay.entity.SecondaryFlatware;
import com.mamay.type.BladeType;
import com.mamay.type.FlatwareType;
import com.mamay.type.HelveType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

@Log4j2
public class FlatwareHandler extends DefaultHandler {

  private Set<Flatware> flatwares;
  private Flatware current = null;
  private String currentTag = null;

  public FlatwareHandler() {
    flatwares = new HashSet<Flatware>();
  }

  public Set<Flatware> getFlatwares() {
    return flatwares;
  }

  public void startElement(String uri, String localName, String qName, Attributes attrs) {
    if ("primary-flatware".equals(localName)) {
      current = new PrimaryFlatware();
      buildFlatware(attrs);

      ((PrimaryFlatware) current).setId(attrs.getValue("id"));
    } else if ("secondary-flatware".equals(localName)) {
      current = new SecondaryFlatware();
      buildFlatware(attrs);

      try {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date production = format.parse(attrs.getValue("production-date"));
        ((SecondaryFlatware) current).setProductionDate(production);
      } catch (ParseException e) {
        log.error(e);
      }

      int durabIndex = attrs.getIndex(uri, "durability");
      if (durabIndex != -1) {
        int durability = Integer.parseInt(attrs.getValue(durabIndex));
        ((SecondaryFlatware) current).setDurability(durability);
      }
    } else {
      currentTag = localName.toLowerCase();
    }
  }

  private void buildFlatware(Attributes attrs) {
    current.setOrigin(attrs.getValue("origin"));
    int valueIndex = attrs.getIndex("value");
    if (valueIndex != -1) {
      boolean value = Boolean.parseBoolean(attrs.getValue(valueIndex));
      current.setValue(value);
    }
  }

  public void endElement(String uri, String localName, String qName) {
    if ("primary-flatware".equals(localName) || "secondary-flatware".equals(localName)) {
      flatwares.add(current);
    }
  }

  public void characters(char[] ch, int start, int length) {
    String s = new String(ch, start, length).trim();
    if (currentTag != null) {
      switch (currentTag) {
        case "type" -> {
          FlatwareType ftype = FlatwareType.valueOf(s.toUpperCase());
          current.setFlatware(ftype);
        }
        case "price" -> {
          int price = Integer.parseInt(s);
          ((PrimaryFlatware) current).setPrice(price);
        }
        case "dish" -> ((SecondaryFlatware) current).addDish(s);
        case "visual" -> current.setVisual(new Flatware.Visual());
        case "blade-length" -> {
          int blength = Integer.parseInt(s);
          current.getVisual().setBladeLength(blength);
        }
        case "blade-width" -> {
          int bwidth = Integer.parseInt(s);
          current.getVisual().setBladeWidth(bwidth);
        }
        case "prong-length" -> {
          int plength = Integer.parseInt(s);
          current.getVisual().setProngLength(plength);
        }
        case "blade-material" -> {
          BladeType btype = BladeType.valueOf(s.toUpperCase());
          current.getVisual().setBlade(btype);
        }
        case "helve" -> {
          HelveType htype = HelveType.valueOf(s.toUpperCase());
          current.getVisual().setHelve(htype);
        }
      }
      currentTag = null;
    }
  }
}
