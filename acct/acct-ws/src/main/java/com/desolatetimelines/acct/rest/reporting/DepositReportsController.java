package com.desolatetimelines.acct.rest.reporting;

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

@Controller
@RequestMapping(value = "/reports/deposits")
public class DepositReportsController {

	@Autowired
	AccountReportingService reportingService;

	@GetMapping("/monthlyBalance")
	@ResponseBody
	@Transactional
	public Stream<MonthlyBalanceReportRecord> list(@RequestParam int startMonth) {
		return reportingService.monthlyDepositsReport(startMonth);
	}
}
