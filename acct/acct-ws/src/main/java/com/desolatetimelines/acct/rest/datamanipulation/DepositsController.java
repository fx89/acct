package com.desolatetimelines.acct.rest.datamanipulation;

import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desolatetimelines.acct.rest.model.DepositRequest;
import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.Exception.AccountServiceException;
import com.desolatetimelines.acct.service.dao.model.Deposit;

@Controller
@RequestMapping(value = "/deposits")
public class DepositsController {
	@Autowired
	AccountService accountService;

	@GetMapping("/list")
	@ResponseBody
	@Transactional
	public Stream<Deposit> list() {
		return accountService.listAllActiveDeposits();
	}

	@PostMapping("/save")
	@ResponseBody
	@Transactional
	public Deposit save(@RequestBody DepositRequest request) throws AccountServiceException {
		return accountService.saveDeposit(request, false);
	}

	@PostMapping("/saveAccountNumber")
	@ResponseBody
	@Transactional
	public Deposit saveAccountNumber(@RequestBody DepositRequest request) throws AccountServiceException {
		return accountService.saveDeposit(request, true);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Transactional
	public String delete(@RequestParam Long id) {
		accountService.deleteDeposit(id);
		return "OK";
	}

}
