package com.desolatetimelines.acct.service.currency;

import com.desolatetimelines.acct.service.currency.exception.BnrCurrencyExtractorException;
import com.desolatetimelines.acct.service.currency.exception.BnrCurrencyExtractorParserException;
import com.desolatetimelines.acct.service.currency.exception.CurrencyExtractorException;
import com.desolatetimelines.acct.service.currency.model.BnrCurrencyExtractorHistoryRecord;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.desolatetimelines.util.xml.XmlUtils.getRootNode;

@Service("BnrCurrencyExtractor")
public class BnrCurrencyExtractor extends UriCurrencyExtractor {
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
	public String getBankName() {
		return "BNR";
	}

	@Override
	protected String mapCurrencyTypeToResourceURI(CurrencyType currencyType)
			throws BnrCurrencyExtractorException
	{
		String currencyURI = CURRENCY_UNIS.get(currencyType);

		if (currencyURI == null) {
			throw new BnrCurrencyExtractorException("The URI for the requested currency is not mapped");
		}

		return currencyURI;
	}

	@Override
	protected List<CurrencyExtractorHistoryRecord> parsePageContent(
		String pageContent,
		CurrencyType currencyType
	) throws CurrencyExtractorException
	{
		try {
			Element rootNode = getRootNode(pageContent);
			NodeList nodeList = rootNode.getElementsByTagName("item");
			List<CurrencyExtractorHistoryRecord> currencyHistory = new ArrayList<>();

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				String titleNodeContent = node.getChildNodes().item(1).getTextContent();
				currencyHistory.add(parseTitleNode(titleNodeContent));
			}

			return currencyHistory;
		}
		catch (ParserConfigurationException | SAXException | IOException exc) {
			throw new BnrCurrencyExtractorException("Parser error: " + exc.getMessage(), exc);
		}
		catch (ParseException e) {
			throw new BnrCurrencyExtractorParserException(e.getMessage(), e);
		}
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
}
