package com.desolatetimelines.acct.service.currency;

import com.desolatetimelines.acct.service.currency.exception.BtCurrencyExtractorException;
import com.desolatetimelines.acct.service.currency.exception.CurrencyExtractorException;
import com.desolatetimelines.acct.service.currency.model.BtCurrencyExtractorRecord;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.desolatetimelines.util.xml.XmlUtils.getRootNode;
import static java.util.Collections.singletonList;

@Service("BtCurrencyExtractor")
public class BtCurrencyExtractor extends UriCurrencyExtractor {

	private static final String RESOURCE_URI = "https://www.bancatransilvania.ro/jsn/exchange.php";

	private static final String SOURCE_NODE_NAME = "buy";

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public String getBankName() {
		return "Banca Transilvania";
	}

	@Override
	protected String mapCurrencyTypeToResourceURI(CurrencyType currencyType) {
		return RESOURCE_URI;
	}

	@Override
	protected List<CurrencyExtractorHistoryRecord> parsePageContent(
		String pageContent,
		CurrencyType currencyType
	) throws CurrencyExtractorException
	{
		try {
			// Parse the document content as XML
			Element rootNode = getRootNode(pageContent);

			// Get the date
			Node updateDateNode = rootNode.getChildNodes().item(0);
			Date date = DATE_FORMAT.parse(updateDateNode.getAttributes().item(0).getTextContent());

			// Get the currency nodes list
			Node exchangeRatesNode = rootNode.getChildNodes().item(1);
			NodeList currencyNodes = exchangeRatesNode.getChildNodes();

			// Prepare a variable to hold the records of the currency
			// Actually, there's ony one record, but who knows, maybe
			// they'll add some more in the future.
			List<CurrencyExtractorHistoryRecord> ret = null;

			// Run through each currency node
			for (int i = 0 ; i < currencyNodes.getLength() ; i++) {
				// Extract the currency node
				Node currencyNode = currencyNodes.item(i);

				// If the currency node has the list for the proper currency
				if (isCurrencyNodeForTheRequestedCurrency(currencyNode, currencyType)) {
					// Get the currency values
					ret = parseCurrencyNode(currencyNode, date);
					break;
				}
			}

			// If, for some reason, the XML response is missing the value(s)
			// for the requested currency, then an exception must be thrown
			if (ret == null) {
				throw new BtCurrencyExtractorException(
						"Cannot find " + SOURCE_NODE_NAME + " value for " + currencyType.name()
					);
			}

			// If all goes well, the value(s) found for the requested currency
			// must be returned
			return ret;

		} catch (ParserConfigurationException | SAXException | IOException exc) {
			throw new BtCurrencyExtractorException("Parser error: " + exc.getMessage(), exc);
		}
		catch (ParseException e) {
			throw new BtCurrencyExtractorException("Unable to parse the date: " + e.getMessage(), e);
		}
	}

	private boolean isCurrencyNodeForTheRequestedCurrency(
		Node currencyNode,
		CurrencyType requestedCurrency
	) {
		String currencyName = currencyNode.getAttributes().getNamedItem("name").getTextContent();
		return requestedCurrency.name().equals(currencyName);

	}

	/**
	 * There is just one currency value in each node, unfortunately
	 */
	private List<CurrencyExtractorHistoryRecord> parseCurrencyNode(Node currencyNode, Date date) {
		NodeList childNodes = currencyNode.getChildNodes();

		for (int i = 0 ; i < childNodes.getLength() ; i++) {
			Node childNode = childNodes.item(i);

			if (SOURCE_NODE_NAME.equals(childNode.getNodeName())) {
				Double rate
					= Double.parseDouble(
							childNode.getChildNodes().item(0).getTextContent()
						);

				return singletonList(
							new BtCurrencyExtractorRecord(date, rate)
						);
			}
		}

		return null;
	}
}
