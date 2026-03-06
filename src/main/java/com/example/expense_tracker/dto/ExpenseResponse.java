package com.example.expense_tracker.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ExpenseResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal amount;
    private String category;
    private LocalDate date;
}
