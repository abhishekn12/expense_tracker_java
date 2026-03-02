package com.example.expense_tracker.service;

import com.example.expense_tracker.Expense;
import com.example.expense_tracker.exceptions.ResourceNotFoundException;
import com.example.expense_tracker.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ExpenseServiceImplTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    private Expense expense;

    @BeforeEach
    public void setUp() {
        expense = new Expense();
        expense.setId(1L);
        expense.setName("Internet Bill");
        expense.setCategory("Utilities");
        expense.setAmount(new BigDecimal("50.00"));
        expense.setDate(LocalDate.now());
    }

    @Test
    void getExpenseById_ShouldReturnExpense_WhenIdExists() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));

        Expense foundExpense = expenseService.getExpenseById(1L);

        // Assert
        assertThat(foundExpense).isNotNull();
        assertThat(foundExpense.getName()).isEqualTo("Internet Bill");
        verify(expenseRepository, times(1)).findById(1L);
    }

    @Test
    void getExpenseById_ShouldThrowException_WhenIdDoesNotExist() {
        when(expenseRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            expenseService.getExpenseById(2L);
        });
    }

    @Test
    void saveExpenseDetails_ShouldReturnSavedExpense() {
        when(expenseRepository.save(any(Expense.class))).thenReturn(expense);
        Expense savedExpense = expenseService.saveExpenseDetails(expense);
        assertThat(savedExpense).isNotNull();
        assertThat(savedExpense.getAmount()).isEqualTo(new BigDecimal("50.00"));
    }
}
