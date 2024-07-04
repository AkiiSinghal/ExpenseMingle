package com.ExpenseMingle.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ExpenseMingle.example.models.*;
import com.ExpenseMingle.example.repositories.*;

@RestController
@RequestMapping("api/expenses")
public class ExpenseController {
	@Autowired
	private ExpenseRepository expenseRepository;

	public ExpenseController(ExpenseRepository expenseRepository) {
		super();
		this.expenseRepository = expenseRepository;
	}

	@GetMapping
	public List<Expense> getExpenseList() {
		return expenseRepository.findAll();
	}
}
