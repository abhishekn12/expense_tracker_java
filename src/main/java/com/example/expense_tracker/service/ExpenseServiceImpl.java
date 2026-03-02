package com.example.expense_tracker.service;

import com.example.expense_tracker.Expense;
import com.example.expense_tracker.exceptions.ResourceNotFoundException;
import com.example.expense_tracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Page<Expense> getAllExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Expense with id " + id + " not found"));
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long Id, Expense expense) {
        Expense existingExpense = getExpenseById(Id);

        if(expense.getName() != null && !expense.getName().isEmpty()) {
            existingExpense.setName(expense.getName());
        }
        if(expense.getDescription() != null) existingExpense.setDescription(expense.getDescription());
        if(expense.getAmount() != null) existingExpense.setAmount(expense.getAmount());
        if(expense.getCategory() != null) existingExpense.setCategory(expense.getCategory());
        if (expense.getDate() != null) existingExpense.setDate(expense.getDate());

        return expenseRepository.save(existingExpense);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.deleteById(expense.getId());

    }

    @Override
    public Page<Expense> getExpensesByCategory(String category, Pageable pageable) {
        return expenseRepository.findByCategory(category, pageable);
    }
}
