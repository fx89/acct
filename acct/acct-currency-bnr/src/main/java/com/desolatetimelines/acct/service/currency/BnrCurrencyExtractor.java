package com.desolatetimelines.acct.service.currency;

import java.io.IOException;
import java.io.StringReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.desolatetimelines.acct.service.currency.exception.BnrCurrencyExtractorException;
import com.desolatetimelines.acct.service.currency.exception.BnrCurrencyExtractorParserException;
import com.desolatetimelines.acct.service.currency.exception.CurrencyExtractorException;
import com.desolatetimelines.acct.service.currency.model.BnrCurrencyExtractorHistoryRecord;

public class BnrCurrencyExtractor implements CurrencyExtractor {
	private static final Map<CurrencyType, String> CURRENCY_UNIS;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	static {
		CURRENCY_UNIS = new HashMap<>();
		CURRENCY_UNIS.put(CurrencyType.EUR, "https://www.bnr.ro/RSS_200003_EUR.aspx");
		CURRENCY_UNIS.put(CurrencyType.CHF, "https://www.bnr.ro/RSS_200009_CHF.aspx");
		CURRENCY_UNIS.put(CurrencyType.GBP, "https://www.bnr.ro/RSS_200014_GBP.aspx");
		CURRENCY_UNIS.put(CurrencyType.USD, "https://www.bnr.ro/RSS_200004_USD.aspx");
	}

	@Override
	public List<CurrencyExtractorHistoryRecord> fetchLatestRecords(CurrencyType currencyType)
			throws CurrencyExtractorException {
		String currencyURI = CURRENCY_UNIS.get(currencyType);

		if (currencyURI == null) {
			throw new BnrCurrencyExtractorException("The URI for the requested currency is not mapped");
		}

		String pageContent = getPageContent(currencyURI);

		return parseCurrencyResponseXML(pageContent);
	}

	private String getPageContent(String currencyURI) throws BnrCurrencyExtractorException {

		SSLConnectionSocketFactory csf = makeSSLConnectionSocketFactory();

		try (CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build()) {
			HttpGet request = new HttpGet(currencyURI);

			try (CloseableHttpResponse response = httpClient.execute(request)) {
				HttpEntity entity = response.getEntity();

				if (entity == null) {
					throw new BnrCurrencyExtractorException("Received empty response from [" + currencyURI + "]");
				}

				return EntityUtils.toString(entity);
			} catch (Exception exc) {
				throw new BnrCurrencyExtractorException("Error consuming response : " + exc.getMessage(), exc);
			}

		} catch (Exception exc) {
			throw new BnrCurrencyExtractorException(
					"Error executing GET request [" + currencyURI + "]: " + exc.getMessage(), exc);
		}
	}

	private SSLConnectionSocketFactory makeSSLConnectionSocketFactory() throws BnrCurrencyExtractorException {
		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustAllStrategy());
			return new SSLConnectionSocketFactory(builder.build());
		} catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException exc) {
			throw new BnrCurrencyExtractorException("Error building SSLConnectionSocketFactory: " + exc.getMessage(),
					exc);
		}
	}

	private List<CurrencyExtractorHistoryRecord> parseCurrencyResponseXML(String currencyResponseXML)
			throws BnrCurrencyExtractorParserException {
		
		Element rootNode = getRootNode(currencyResponseXML);

		NodeList nodeList = rootNode.getElementsByTagName("item");

		List<CurrencyExtractorHistoryRecord> currencyHistory = new ArrayList<>();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			String titleNodeContent = node.getChildNodes().item(1).getTextContent();

			try {
				currencyHistory.add(parseTitleNode(titleNodeContent));
			} catch (ParseException e) {
				throw new BnrCurrencyExtractorParserException(e.getMessage(), e);
			}

		}

		return currencyHistory;
	}

	private CurrencyExtractorHistoryRecord parseTitleNode(String titleNodeContent) throws ParseException {
		CurrencyExtractorHistoryRecord rec = new BnrCurrencyExtractorHistoryRecord();

		String[] tokens = titleNodeContent.split(" ");
		String strValue = tokens[3];
		String strDate = tokens[5];
		
		rec.setValue(Double.parseDouble(strValue));
		rec.setDate(DATE_FORMAT.parse(strDate));

		return rec;
	}

	private Element getRootNode(String currencyResponseXML) throws BnrCurrencyExtractorParserException {
		try {
			InputSource is = new InputSource(new StringReader(currencyResponseXML));

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(is);

			return doc.getDocumentElement();
		} catch (ParserConfigurationException | SAXException | IOException exc) {
			throw new BnrCurrencyExtractorParserException("Parser error: " + exc.getMessage(), exc);
		}
	}

}
