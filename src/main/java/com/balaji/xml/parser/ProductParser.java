package com.balaji.xml.parser;

import com.balaji.xml.domain.Product;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *  This class represents how to parse the xml child elements and populate into domain object
 *
 * @author Balaji Nagarajan
 * @version 1.0
 */
public class ProductParser {


    /**
     *
     * @param args
     * @throws XMLStreamException
     */
    public static void main(String[] args) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();

        XMLEventReader reader = factory.createXMLEventReader(ClassLoader.getSystemResourceAsStream("xml/product.xml"));

        List<Product> prodList = parseProductXML(reader);
        for (Product prod : prodList) {
            System.out.println(prod.toString());
        }
    }

    /**
     *
     * @param xmlEventReader
     * @return
     * @throws XMLStreamException
     */
    private static List<Product> parseProductXML(XMLEventReader xmlEventReader) throws XMLStreamException {
        List<Product> prodList = new ArrayList<>();
        Product prod = null;

        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                if (startElement.getName().getLocalPart().equalsIgnoreCase("product")) {
                        prod = new Product();
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if(idAttr != null){
                            prod.setId(Long.parseLong(idAttr.getValue()));
                        }
                }
                else if (startElement.getName().getLocalPart().equalsIgnoreCase("UPC")) {
                    xmlEvent = xmlEventReader.nextEvent();
                    prod.setUpc(Long.parseLong(xmlEvent.asCharacters().getData()));
                } else if (startElement.getName().getLocalPart().equalsIgnoreCase("ItemDesc")) {
                    xmlEvent = xmlEventReader.nextEvent();
                    prod.setItemDesc(xmlEvent.asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equalsIgnoreCase("UnitPrice")) {
                    xmlEvent = xmlEventReader.nextEvent();
                    prod.setUnitPrice(Double.parseDouble(xmlEvent.asCharacters().getData()));
                } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Category")) {
                    xmlEvent = xmlEventReader.nextEvent();
                    prod.setCategory(xmlEvent.asCharacters().getData());
                }
            }
            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if (endElement.getName().getLocalPart().equalsIgnoreCase("Product")) {
                    prodList.add(prod);
                }
            }
        }

        return prodList;
    }

}
