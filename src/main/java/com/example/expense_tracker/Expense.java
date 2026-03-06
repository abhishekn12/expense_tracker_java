package com.example.expense_tracker;

import com.example.expense_tracker.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Expense name must not be blank")
    @Size(min = 3, message = "Expense name must be atleast 3 chars")
    private String name;

    private String description;

    @Column(nullable = false)
    @NotNull(message = "amount needed")
    @Positive(message = ">0")
    private BigDecimal amount;

    @Column(nullable = false)
    @NotBlank(message = "Category is needed")
    private String category;

    @Column(nullable = false)
    @NotNull(message = "Data needed")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
