package com.desolatetimelines.acct;

import com.desolatetimelines.acct.service.AccountReportingService;
import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.currency.CurrencyExtractor;
import com.desolatetimelines.acct.service.dao.AccountDataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
@EnableRetry
@ComponentScan("com.desolatetimelines.acct")
public class AccountingApplication {

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(AccountingApplication.class, args);

		AccountService accService = (AccountService) appContext.getBean("AccountService");
		AccountReportingService repService = (AccountReportingService) appContext.getBean("AccountReportingService");

		// AccountDataService dataService = new InMemoryAccountDataServiceImpl().withMockData();
		AccountDataService dataService = (AccountDataService) appContext.getBean("SpringDataAccountDataService");

		accService.setDataService(dataService);
		repService.setDataService(dataService);

		addAllAvailableCurrencyExtractors(appContext, accService);
	}

	private static void addAllAvailableCurrencyExtractors(
		ApplicationContext applicationContext,
		AccountService acctService
	) {
		Map<String, CurrencyExtractor> currencyExtractors
			= applicationContext.getBeansOfType(CurrencyExtractor.class);

		if (!currencyExtractors.isEmpty()) {
			currencyExtractors.values().forEach(currencyExtractor ->
					acctService.putCurrencyExtractor(currencyExtractor.getBankName(), currencyExtractor)
				);
		}
	}

}
