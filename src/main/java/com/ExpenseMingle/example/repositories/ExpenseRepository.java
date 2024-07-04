package com.ExpenseMingle.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ExpenseMingle.example.models.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
