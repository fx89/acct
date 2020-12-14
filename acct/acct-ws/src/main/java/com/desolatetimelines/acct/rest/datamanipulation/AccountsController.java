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

import com.desolatetimelines.acct.rest.model.AccountRequest;
import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.Exception.AccountServiceBusinessException;
import com.desolatetimelines.acct.service.Exception.AccountServiceValidationException;
import com.desolatetimelines.acct.service.dao.model.Account;
import com.desolatetimelines.acct.service.model.AccountSummary;

@Controller
@RequestMapping(value = "/accounts")
public class AccountsController {

	@Autowired
	AccountService accountService;

	@GetMapping("/list")
	@ResponseBody
	@Transactional
	public Stream<Account> list() {
		return accountService.listAccounts();
	}

	@PostMapping("/save")
	@ResponseBody
	@Transactional
	public Account save(@RequestBody AccountRequest request) throws AccountServiceValidationException {
		return accountService.saveAccount(request);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Transactional
	public String delete(@RequestParam Long id) {
		accountService.deleteAccount(id);
		return "OK";
	}

	@GetMapping("/summary")
	@ResponseBody
	@Transactional
	public AccountSummary getAccountSummary(@RequestParam Long accountId) {
		return accountService.getAccountSummary(accountId);
	}

	@GetMapping("/transfer")
	@ResponseBody
	@Transactional
	public String transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId,
			@RequestParam Double amount) throws AccountServiceBusinessException {
		accountService.transferAmount(fromAccountId, toAccountId, amount);
		return "OK";
	}
}
