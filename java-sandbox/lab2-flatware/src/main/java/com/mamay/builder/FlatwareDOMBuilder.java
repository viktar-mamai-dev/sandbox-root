package com.mamay.builder;

import com.mamay.entity.Flatware;
import com.mamay.entity.PrimaryFlatware;
import com.mamay.entity.SecondaryFlatware;
import com.mamay.exception.LogicalException;
import com.mamay.type.BladeType;
import com.mamay.type.FlatwareType;
import com.mamay.type.HelveType;
import lombok.extern.log4j.Log4j2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Log4j2
public class FlatwareDOMBuilder extends AbstractFlatwareBuilder {

    private DocumentBuilder docBuilder;

    public FlatwareDOMBuilder() {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = documentFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.error(e);
        }
    }

    public FlatwareDOMBuilder(Set<Flatware> flatwares) {
        super(flatwares);
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = documentFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.error(e);
        }
    }

    @Override
    public void buildFlatwares(String filename) throws LogicalException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new LogicalException("Such file does not exist!");
        }

        try {
            Document doc = docBuilder.parse(file);
            Element root = doc.getDocumentElement();

            NodeList primaryList = root.getElementsByTagName("primary-flatware");
            HashSet<PrimaryFlatware> primaryFlatwares = new HashSet<PrimaryFlatware>();
            for (int i = 0; i < primaryList.getLength(); i++) {
                Element flatwareElement = (Element) primaryList.item(i);
                PrimaryFlatware flatware = buildPrimaryFlatware(flatwareElement);
                primaryFlatwares.add(flatware);
            }

            NodeList secondaryList = root.getElementsByTagName("secondary-flatware");
            HashSet<SecondaryFlatware> secondaryFlatwares = new HashSet<SecondaryFlatware>();
            for (int i = 0; i < secondaryList.getLength(); i++) {
                Element flatwareElement = (Element) secondaryList.item(i);
                SecondaryFlatware flatware = buildSecondaryFlatware(flatwareElement);
                secondaryFlatwares.add(flatware);
            }

            this.flatwares.addAll(primaryFlatwares);
            this.flatwares.addAll(secondaryFlatwares);
        } catch (SAXException | IOException e) {
            log.error(e);
        }
    }

    private Flatware buildFlatware(Element element, Flatware flatware) {
        flatware.setOrigin(element.getAttribute("origin"));
        if (element.hasAttribute("value")) {
            flatware.setValue(Boolean.parseBoolean(element.getAttribute("value")));
        }
        String flatwareType = getElementTextContent(element, "type");
        switch (flatwareType.trim()) {
            case "spoon" -> flatware.setFlatware(FlatwareType.SPOON);
            case "fork" -> flatware.setFlatware(FlatwareType.FORK);
            case "scapula" -> flatware.setFlatware(FlatwareType.SCAPULA);
            case "scissors" -> flatware.setFlatware(FlatwareType.SCISSORS);
            case "pincers" -> flatware.setFlatware(FlatwareType.PINCERS);
            case "knife" -> {
                flatware.setFlatware(FlatwareType.KNIFE);
                Element visualElement = (Element) element.getElementsByTagName("visual").item(0);
                buildVisual(visualElement, flatware);
            }
        }
        return flatware;
    }

    private PrimaryFlatware buildPrimaryFlatware(Element element) {
        PrimaryFlatware fw = new PrimaryFlatware();
        fw = (PrimaryFlatware) buildFlatware(element, fw);
        fw.setPrice(Integer.parseInt(getElementTextContent(element, "price")));
        fw.setId(element.getAttribute("id"));
        return fw;
    }

    private SecondaryFlatware buildSecondaryFlatware(Element element) {
        SecondaryFlatware fw = new SecondaryFlatware();
        fw = (SecondaryFlatware) buildFlatware(element, fw);
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            fw.setProductionDate(format.parse(element.getAttribute("production-date")));
        } catch (ParseException e) {
            log.error(e);
        }
        if (element.hasAttribute("durability")) {
            int durability = Integer.parseInt(element.getAttribute("durability"));
            fw.setDurability(durability);
        }
        fw.setDishes(getElementsTextContent(element, "dish"));
        return fw;
    }

    private void buildVisual(Element visualElement, Flatware flatware) {
        Flatware.Visual visual = new Flatware.Visual();
        String blade = getElementTextContent(visualElement, "blade-material");
        String helve = getElementTextContent(visualElement, "helve");
        int bladeLength = Integer.parseInt(getElementTextContent(visualElement, "blade-length"));
        int bladeWidth = Integer.parseInt(getElementTextContent(visualElement, "blade-width"));
        int prongLength = Integer.parseInt(getElementTextContent(visualElement, "prong-length"));
        visual.setBladeLength(bladeLength);
        visual.setBladeWidth(bladeWidth);
        visual.setProngLength(prongLength);
        switch (blade) {
            case "cast iron" -> visual.setBlade(BladeType.CAST_IRON);
            case "copper" -> visual.setBlade(BladeType.COPPER);
            case "steel" -> visual.setBlade(BladeType.STEEL);
        }
        switch (helve) {
            case "wood" -> visual.setHelve(HelveType.WOOD);
            case "plastic" -> visual.setHelve(HelveType.PLASTIC);
            case "metal" -> visual.setHelve(HelveType.METAL);
            case "leather" -> visual.setHelve(HelveType.LEATHER);
            case "rubber" -> visual.setHelve(HelveType.RUBBER);
        }
        flatware.setVisual(visual);
    }

    private String getElementTextContent(Element element, String elementName) {
        Node node = element.getElementsByTagName(elementName).item(0);
        return node.getTextContent();
    }

    private Set<String> getElementsTextContent(Element element, String elementName) {
        Set<String> dishes = new HashSet<String>();
        NodeList dishList = element.getElementsByTagName(elementName);
        for (int i = 0; i < dishList.getLength(); i++) {
            Node dishElement = dishList.item(i);
            dishes.add(dishElement.getTextContent());
        }
        return dishes;
    }
}
