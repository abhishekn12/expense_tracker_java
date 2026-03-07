package com.example.expense_tracker.controller;


import com.example.expense_tracker.Expense;
import com.example.expense_tracker.dto.ExpenseRequest;
import com.example.expense_tracker.dto.ExpenseResponse;
import com.example.expense_tracker.service.ExpenseService;
import com.example.expense_tracker.util.ExpenseMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/expenses")
    public Page<ExpenseResponse> getAllExpenses(Pageable pageable) {
        // We map the Page of Entities to a Page of DTOs
        return expenseService.getAllExpenses(pageable).map(ExpenseMapper::mapToResponse);
    }

    @GetMapping("/expenses/{id}")
    public ExpenseResponse getExpenseById(@PathVariable Long id) {
        return ExpenseMapper.mapToResponse(expenseService.getExpenseById(id));
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public ExpenseResponse saveExpenseDetails(@Valid @RequestBody ExpenseRequest request) {
        return expenseService.saveExpenseDetails(request);
    }

    // FIX 1: Use ExpenseRequest for input and ExpenseResponse for output
    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@PathVariable Long id, @RequestBody ExpenseRequest request) {
        return expenseService.updateExpenseDetails(id, request);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{id}")
    public void deleteExpenseById(@PathVariable Long id) {
        expenseService.deleteExpenseById(id);
    }

    // FIX 2: Map the category results to DTOs to avoid the recursion error here too
    @GetMapping("/expenses/category")
    public Page<ExpenseResponse> getExpensesByCategory(@RequestParam String category, Pageable pageable) {
        return expenseService.getExpensesByCategory(category, pageable).map(ExpenseMapper::mapToResponse);
    }
}
