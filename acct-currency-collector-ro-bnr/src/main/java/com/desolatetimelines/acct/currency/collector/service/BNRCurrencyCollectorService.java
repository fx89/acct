package com.desolatetimelines.acct.currency.collector.service;

import com.desolatetimelines.acct.currency.collector.exception.AcctCurrencyCollectorServiceException;
import com.desolatetimelines.acct.currency.collector.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
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
import java.time.Instant;
import java.util.*;

@Service
public class BNRCurrencyCollectorService implements CurrencyCollectorService<BNRCurrencyCollectionSession> {

    /**
     * Supported bank codes
     */
    private static final Collection<String> SUPPORTED_BANK_CODES = List.of("BNR");

    /**
     * Web client for the last available day's currency exchange rates (all vs RON)
     */
    private final WebClient webClient =
        WebClient.builder()
            .baseUrl("https://curs.bnr.ro/nbrfxrates.xml")
            .build();

    @Override
    public Collection<String> getSupportedBankCodes() {
        return SUPPORTED_BANK_CODES;
    }

    @Override
    public BNRCurrencyCollectionSession startSession(SessionParameters sessionParameters) {
        return new BNRCurrencyCollectionSession(collectRates());
    }

    @Override
    public Collection<CollectedCurrencyExchangeRecord> collectRecords(
        BNRCurrencyCollectionSession session,
        String bankCode,
        String currencyCode
    ) {
        // If called for an unsupported bank, raise an exception
        if (!SUPPORTED_BANK_CODES.contains(bankCode)) {
            throw new IllegalArgumentException("Unsupported bank code: " + bankCode);
        }

        // Find the required currency
        final CubeRate requestedCurrencyRate =
            session.cube().rates().stream()
                .filter(rate -> Objects.equals(currencyCode, rate.currency()))
                .findFirst()
                .orElseThrow(() -> new AcctCurrencyCollectorServiceException(
                    "Currency code " + currencyCode + " not found"
                ));

        // Compute the exchange rate
        final double exchangeRate =
            requestedCurrencyRate.rate() * Optional.ofNullable(requestedCurrencyRate.multiplier()).orElse(1d);

        // Return the data for the required currency
        return
            List.of(
                new CollectedCurrencyExchangeRecord(
                    session.cube().recordDate(),
                    exchangeRate,
                    exchangeRate
                )
            );
    }

    @Override
    public void endSession(BNRCurrencyCollectionSession session) {

    }

    private Cube collectRates() {
        // Get the data from the bank
        String xmlContent = webClient.get().retrieve().bodyToMono(String.class).block();

        // Parse the data for the currency code
        final Document xmlDocument = parseXmlContent(xmlContent);

        // Get the Body node
        final Node bodyNode =
            findChildNodeByName(xmlDocument.getDocumentElement(), "Body")
                .orElseThrow(() -> new AcctCurrencyCollectorServiceException("Body node not found"));

        // Get the Cube node
        final Node cubeNode =
            findChildNodeByName(bodyNode, "Cube")
                .orElseThrow(() -> new AcctCurrencyCollectorServiceException("Cube node not found"));

        // Get the record date
        final Instant recordDate =
            Instant.parse(cubeNode.getAttributes().getNamedItem("date").getNodeValue() + "T00:00:00.000000Z");

        // Initialize the rates collection
        final Collection<CubeRate> cubeRates = new ArrayList<>(50);

        // Look for the currency within the child nodes of the cube
        for (int i = 0; i < cubeNode.getChildNodes().getLength(); i++) {
            // Get the node
            final Node node = cubeNode.getChildNodes().item(i);

            // Get the node properties
            final String nodeCurrencyCode = node.getAttributes().getNamedItem("currency").getNodeValue();
            final Double nodeRate = Optional.ofNullable(node.getTextContent()).map(Double::parseDouble).orElse(null);
            final Double nodeMultiplier = Optional.ofNullable(node.getAttributes().getNamedItem("multiplier")).map(Node::getNodeValue).map(Double::parseDouble).orElse(null);

            // Create and add the cube rate
            cubeRates.add(new CubeRate(nodeCurrencyCode, nodeRate, nodeMultiplier));
        }

        // Create and return a reference to the cube
        return new Cube(recordDate, cubeRates);
    }

    private static Document parseXmlContent(String xmlContent) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(xmlContent));
            return builder.parse(inputSource);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new AcctCurrencyCollectorServiceException("Unable to parse XML content: " + e.getMessage(), e);
        }
    }

    private Optional<Node> findChildNodeByName(Node parentNode, String name) {
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
