package com.ExpenseMingle.example.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ExpenseMingle.example.models.Expense;
import com.ExpenseMingle.example.repositories.ExpenseRepository;

@SpringBootTest
public class ExpenseControllerTest {
	
	@Mock
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private ExpenseController expenseController;
	
	@BeforeEach
	void SetUp() {
		this.expenseController = new ExpenseController(expenseRepository);
	}
	
	@Test
	void testGetExpenseList() {
		when(expenseRepository.findAll()).thenReturn(Stream
				.of(new Expense(Long.valueOf(1), "qwe", "34", "1", Long.valueOf(1), null, null)
				  , new Expense(Long.valueOf(2), "cvb", "89", "2", Long.valueOf(1), null, null))
				.collect(Collectors.toList()));
		assertEquals(2, expenseController.getExpenseList().size());
	}
}
