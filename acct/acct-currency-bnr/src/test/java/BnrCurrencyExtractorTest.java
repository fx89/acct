import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.desolatetimelines.acct.service.currency.BnrCurrencyExtractor;
import com.desolatetimelines.acct.service.currency.CurrencyExtractor;
import com.desolatetimelines.acct.service.currency.CurrencyType;
import com.desolatetimelines.acct.service.currency.exception.CurrencyExtractorException;

public class BnrCurrencyExtractorTest {

	@Test
	public void test() throws CurrencyExtractorException {
		CurrencyExtractor currencyExtractor = new BnrCurrencyExtractor();

		currencyExtractor.fetchLatestRecords(CurrencyType.EUR).forEach(rec -> {
			System.out.println(rec.getDate() + " = " + rec.getValue());
		});

		assertEquals(1, 1);
	}

}
