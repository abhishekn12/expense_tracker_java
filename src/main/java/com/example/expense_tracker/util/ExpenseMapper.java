package com.example.expense_tracker.util;

import com.example.expense_tracker.Expense;
import com.example.expense_tracker.dto.ExpenseRequest;
import com.example.expense_tracker.dto.ExpenseResponse;

public class ExpenseMapper {

    public static Expense mapToEntity(ExpenseRequest request) {
        Expense expense = new Expense();
        expense.setName(request.getName());
        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate());
        return expense;
    }

    public static ExpenseResponse mapToResponse(Expense entity) {
        return ExpenseResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .amount(entity.getAmount())
                .category(entity.getCategory())
                .date(entity.getDate())
                .build();

    }
}
