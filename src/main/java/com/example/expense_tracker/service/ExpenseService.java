package com.example.expense_tracker.service;

import com.example.expense_tracker.Expense;
import com.example.expense_tracker.dto.ExpenseRequest;
import com.example.expense_tracker.dto.ExpenseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {
    Page<Expense> getAllExpenses(Pageable pageable);
    Expense getExpenseById(Long id);
    ExpenseResponse saveExpenseDetails(ExpenseRequest request);
    Expense updateExpenseDetails(Long Id, Expense expense);
    void deleteExpenseById(Long id);
    Page<Expense> getExpensesByCategory(String category, Pageable pageable);
}
