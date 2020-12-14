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

import com.desolatetimelines.acct.rest.model.BankRequest;
import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.Exception.AccountServiceValidationException;
import com.desolatetimelines.acct.service.dao.model.Bank;

@Controller
@RequestMapping(value = "/banks")
public class BanksController {
	@Autowired
	AccountService accountService;

	@GetMapping("/list")
	@ResponseBody
	@Transactional
	public Stream<Bank> list() {
		return accountService.listAllBanks();
	}

	@PostMapping("/save")
	@ResponseBody
	@Transactional
	public Bank save(@RequestBody BankRequest request) throws AccountServiceValidationException {
		return accountService.saveBank(request);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Transactional
	public String delete(@RequestParam Long id) {
		accountService.deleteBank(id);
		return "OK";
	}

}
