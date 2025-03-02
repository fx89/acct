package com.desolatetimelines.acct.common.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;
import java.util.Optional;

public abstract class XmlUtils {

    /**
     * Returns an {@link Document XML document} with the content of the given string
     *
     * @param xmlContent the given string
     */
    public static Document parseXmlContent(String xmlContent) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(xmlContent));
            return builder.parse(inputSource);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException("Unable to parse XML content: " + e.getMessage(), e);
        }
    }

    /**
     * Finds the first child node of the parent node that has the given name
     *
     * @param parentNode a reference to the parent node
     * @param name       the given name
     */
    public static Optional<Node> findChildNodeByName(Node parentNode, String name) {
        final NodeList nodeList = parentNode.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            final Node node = nodeList.item(i);
            if (Objects.equals(name, node.getNodeName())) {
                return Optional.of(node);
            }
        }

        return Optional.empty();
    }

}
