package com.desolatetimelines.acct.rest.datamanipulation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.Exception.AccountServiceBusinessException;
import com.desolatetimelines.acct.service.Exception.AccountServiceException;
import com.desolatetimelines.acct.service.dao.model.CurrencyHistoryRecord;
import com.desolatetimelines.acct.service.dao.model.MonitoredCurrency;

@Controller
@RequestMapping(value = "/currency")
public class MonitoredCurrencyController {
	@Autowired
	AccountService accountService;

	@GetMapping("/list")
	@ResponseBody
	@Transactional
	public Stream<MonitoredCurrency> list() {
		return accountService.getAllMonitoredCurrencies()
					.sorted(
							Comparator
								.comparing(MonitoredCurrency::getCurrencyTypeName)
								.thenComparing(MonitoredCurrency::getBankId)
						);
	}

	@GetMapping("/listSupportedCurrencies")
	@ResponseBody
	@Transactional
	public Stream<String> listSupportedCurrencies() {
		return accountService.getAllSupportedCurrencyTypes();
	}

	@PostMapping("/monitorCurrency")
	@ResponseBody
	@Transactional
	public MonitoredCurrency monitorCurrency(
		@RequestParam String currencyName,
		@RequestParam Long bankId
	) throws AccountServiceBusinessException
	{
		return accountService.monitorCurrency(currencyName, bankId);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Transactional
	public void unmonitorCurrency(@RequestParam Long id) {
		accountService.unmonitorCurrency(id);
	}

	@GetMapping("/updateCurrenciesFromSource")
	@ResponseBody
	@Transactional
	@Retryable
	public void updateCurrenciesFromSource(
		@RequestParam Long bankId
	) throws AccountServiceException {
		accountService.updateCurrenciesFromSource(bankId);
	}

	@GetMapping("/listHistory")
	@ResponseBody
	@Transactional
	public Stream<CurrencyHistoryRecord> listHistory(@RequestParam Long currencyId, @RequestParam String sinceDate)
			throws ParseException {
		return accountService.getCurrencyHistory(currencyId, new SimpleDateFormat("yyyy-MM-dd").parse(sinceDate));
	}
}
