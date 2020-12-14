package com.desolatetimelines.acct.rest.reporting;

import java.text.ParseException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desolatetimelines.acct.service.AccountReportingService;
import com.desolatetimelines.acct.service.model.MonthlyBalanceReportRecord;
import com.desolatetimelines.acct.service.model.MonthlyExpensesReport;

@Controller
@RequestMapping(value = "/reports/accounts")
public class AccountReportsController {

	@Autowired
	AccountReportingService reportingService;

	@GetMapping("/monthlyBalance")
	@ResponseBody
	@Transactional
	public Stream<MonthlyBalanceReportRecord> list(@RequestParam Long accountId, @RequestParam int startMonth) {
		return reportingService.monthlyBalanceReport(accountId, startMonth);
	}

	@GetMapping("/monthlyExpenses")
	@ResponseBody
	@Transactional
	public Stream<MonthlyExpensesReport> list1(@RequestParam int startMonth) throws ParseException {
		return reportingService.monthlyExpensesReports(startMonth);
	}
}
