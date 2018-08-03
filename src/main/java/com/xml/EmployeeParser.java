package com.xml;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  This class represents how to parse the xml child elements
 *
 * @author Balaji Nagarajan
 * @version 1.0
 */
public class EmployeeParser {

    public static void main (String[] args) throws XMLStreamException {

        String tagContent = "employee";
        List<Map<String,String>> childElementList = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(ClassLoader.getSystemResourceAsStream("xml/employee.xml"));

        while(reader.hasNext()) {
            int event = reader.next();
            switch (event) {
                case XMLStreamConstants.START_ELEMENT:
                    if(tagContent.equalsIgnoreCase(reader.getLocalName())) {
                        childElementList.add(getChildElement(reader,tagContent));
                    }
            }
        }
        for(Map map : childElementList)
            map.forEach( (k,v) -> System.out.println( k + ":" + v));
    }

    /**
     *
     * @param reader javax.xml.stream.XMLStreamReader of xml
     * @param tagName element to be parsed
     * @return A Map of all the child elements
     * @throws javax.xml.stream.XMLStreamException exception
     */
    private static Map<String,String> getChildElement(XMLStreamReader reader, String tagName) throws XMLStreamException {

        Map<String,String> childTagMap = new HashMap<>();
        boolean endFlag = false;
        do {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {

                String childTagName = reader.getLocalName().toUpperCase();
                String childTagValue = "";
                int childEvent = reader.next();
                if(childEvent == XMLStreamConstants.CHARACTERS) {
                    childTagValue = reader.getText().trim();
                }
                childTagMap.put(childTagName,childTagValue);
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                if(tagName.equalsIgnoreCase(reader.getLocalName())) {
                    endFlag = true;
                }
            }
        } while (reader.hasNext() && !endFlag);
        return childTagMap;
    }
}

