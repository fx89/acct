package com.desolatetimelines.acct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.retry.annotation.EnableRetry;

import com.desolatetimelines.acct.service.AccountReportingService;
import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.currency.CurrencyExtractor;
import com.desolatetimelines.acct.service.dao.AccountDataService;

@SpringBootApplication
@EnableAutoConfiguration
@EnableRetry
@ComponentScan("com.desolatetimelines.acct")
public class AccountingApplication {

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(AccountingApplication.class, args);

		AccountService accService = (AccountService) appContext.getBean("AccountService");
		CurrencyExtractor curEx = (CurrencyExtractor) appContext.getBean("CurrencyExtractor");
		AccountReportingService repService = (AccountReportingService) appContext.getBean("AccountReportingService");

		// AccountDataService dataService = new
		// InMemoryAccountDataServiceImpl().withMockData();
		AccountDataService dataService = (AccountDataService) appContext.getBean("SpringDataAccountDataService");

		accService.setDataService(dataService);
		accService.setCurrencyExtractor(curEx);
		repService.setDataService(dataService);
	}

}
