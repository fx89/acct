package com.desolatetimelines.acct.rest.datamanipulation;

import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.dao.model.InterestHistoryRecord;

@Controller
@RequestMapping(value = "/interests")
public class InterestHistoryRecordsController {
	@Autowired
	AccountService accountService;

	@GetMapping("/list")
	@ResponseBody
	@Transactional
	public Stream<InterestHistoryRecord> list(@RequestParam Long bankId) {
		return accountService.listInterestHistoryRecords(bankId);
	}

}
