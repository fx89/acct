package com.desolatetimelines.util.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class XmlUtils {

    public static Element getRootNode(String xmlDocumentContent)
    throws ParserConfigurationException, SAXException, IOException
    {
        InputSource is = new InputSource(new StringReader(xmlDocumentContent));

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(is);

        return doc.getDocumentElement();
    }

}
