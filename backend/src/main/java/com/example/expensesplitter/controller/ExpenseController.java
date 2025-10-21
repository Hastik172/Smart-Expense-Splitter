package com.example.expensesplitter.controller;

import com.example.expensesplitter.dto.ExpenseDto;
import com.example.expensesplitter.model.Expense;
import com.example.expensesplitter.model.User;
import com.example.expensesplitter.service.ExpenseService;
import com.example.expensesplitter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @GetMapping
    public List<ExpenseDto> list() {
        return expenseService.listAll().stream().map(e -> {
            ExpenseDto d = new ExpenseDto();
            d.id = e.getId();
            d.groupId = e.getGroup() != null ? e.getGroup().getId() : null;
            d.payerId = e.getPayer() != null ? e.getPayer().getId() : null;
            d.amount = e.getAmount();
            d.currency = e.getCurrency();
            d.description = e.getDescription();
            d.occurredAt = e.getOccurredAt();
            return d;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ExpenseDto create(@RequestBody ExpenseDto dto) {
        Expense e = new Expense();
        if (dto.payerId != null) {
            User payer = userService.findById(dto.payerId);
            e.setPayer(payer);
        }
        e.setAmount(dto.amount);
        e.setCurrency(dto.currency == null ? "USD" : dto.currency);
        e.setDescription(dto.description);
        Expense saved = expenseService.create(e);
        ExpenseDto d = new ExpenseDto();
        d.id = saved.getId();
        d.amount = saved.getAmount();
        d.currency = saved.getCurrency();
        d.description = saved.getDescription();
        d.payerId = saved.getPayer() != null ? saved.getPayer().getId() : null;
        return d;
    }
}
