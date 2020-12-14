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

import com.desolatetimelines.acct.rest.model.IncomeOrExpenseItemRequest;
import com.desolatetimelines.acct.service.AccountService;
import com.desolatetimelines.acct.service.Exception.AccountServiceValidationException;
import com.desolatetimelines.acct.service.dao.model.IncomeOrExpenseItem;

@Controller
@RequestMapping(value = "/items")
public class IncomeOrExpenseItemsController {
	@Autowired
	AccountService accountService;

	@GetMapping("/list")
	@ResponseBody
	@Transactional
	public Stream<IncomeOrExpenseItem> list() {
		return accountService.listIncomeOrExpenseItems();
	}

	@PostMapping("/save")
	@ResponseBody
	@Transactional
	public IncomeOrExpenseItem save(@RequestBody IncomeOrExpenseItemRequest request)
			throws AccountServiceValidationException {
		return accountService.saveIncomeOrExpenseItem(request);
	}

	@DeleteMapping("/delete")
	@ResponseBody
	@Transactional
	public String delete(@RequestParam Long id) {
		accountService.deleteIncomeOrExpenseItem(id);
		return "OK";
	}
}
