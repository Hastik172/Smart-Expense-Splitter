package com.example.expensesplitter.controller;

import com.example.expensesplitter.dto.ExpenseShareDto;
import com.example.expensesplitter.model.Expense;
import com.example.expensesplitter.model.ExpenseShare;
import com.example.expensesplitter.model.User;
import com.example.expensesplitter.service.ExpenseService;
import com.example.expensesplitter.service.ExpenseShareService;
import com.example.expensesplitter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expense-shares")
public class ExpenseShareController {
    private final ExpenseShareService shareService;
    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpenseShareController(ExpenseShareService shareService, ExpenseService expenseService, UserService userService) {
        this.shareService = shareService; this.expenseService = expenseService; this.userService = userService;
    }

    @GetMapping
    public List<ExpenseShareDto> list() {
        return shareService.listAll().stream().map(es -> {
            ExpenseShareDto d = new ExpenseShareDto(); d.id = es.getId(); d.expenseId = es.getExpense() != null ? es.getExpense().getId() : null; d.userId = es.getUser() != null ? es.getUser().getId() : null; d.shareAmount = es.getShareAmount(); d.isSettled = es.isSettled(); return d;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ExpenseShareDto create(@RequestBody ExpenseShareDto dto) {
        ExpenseShare es = new ExpenseShare();
        if (dto.expenseId != null) { Expense e = expenseService.findById(dto.expenseId); es.setExpense(e); }
        if (dto.userId != null) { User u = userService.findById(dto.userId); es.setUser(u); }
        es.setShareAmount(dto.shareAmount);
        ExpenseShare saved = shareService.create(es);
        ExpenseShareDto out = new ExpenseShareDto(); out.id = saved.getId(); out.expenseId = saved.getExpense() != null ? saved.getExpense().getId() : null; out.userId = saved.getUser() != null ? saved.getUser().getId() : null; out.shareAmount = saved.getShareAmount(); out.isSettled = saved.isSettled(); return out;
    }
}
