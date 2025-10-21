package com.example.expensesplitter.controller;

import com.example.expensesplitter.dto.PaymentDto;
import com.example.expensesplitter.model.Payment;
import com.example.expensesplitter.model.Expense;
import com.example.expensesplitter.model.User;
import com.example.expensesplitter.service.PaymentService;
import com.example.expensesplitter.service.ExpenseService;
import com.example.expensesplitter.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;
    private final ExpenseService expenseService;

    public PaymentController(PaymentService paymentService, UserService userService, ExpenseService expenseService) {
        this.paymentService = paymentService; this.userService = userService; this.expenseService = expenseService;
    }

    @GetMapping
    public List<PaymentDto> list() {
        return paymentService.listAll().stream().map(p -> {
            PaymentDto d = new PaymentDto(); d.id = p.getId(); d.fromUserId = p.getFromUser() != null ? p.getFromUser().getId() : null; d.toUserId = p.getToUser() != null ? p.getToUser().getId() : null; d.amount = p.getAmount(); d.currency = p.getCurrency(); d.referenceExpenseId = p.getReferenceExpense() != null ? p.getReferenceExpense().getId() : null; return d;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public PaymentDto create(@RequestBody PaymentDto dto) {
        Payment p = new Payment();
        if (dto.fromUserId != null) { User f = userService.findById(dto.fromUserId); p.setFromUser(f); }
        if (dto.toUserId != null) { User t = userService.findById(dto.toUserId); p.setToUser(t); }
        if (dto.referenceExpenseId != null) { Expense e = expenseService.findById(dto.referenceExpenseId); p.setReferenceExpense(e); }
        p.setAmount(dto.amount); p.setCurrency(dto.currency == null ? "USD" : dto.currency);
        Payment saved = paymentService.create(p);
        PaymentDto out = new PaymentDto(); out.id = saved.getId(); out.fromUserId = saved.getFromUser() != null ? saved.getFromUser().getId() : null; out.toUserId = saved.getToUser() != null ? saved.getToUser().getId() : null; out.amount = saved.getAmount(); out.currency = saved.getCurrency(); out.referenceExpenseId = saved.getReferenceExpense() != null ? saved.getReferenceExpense().getId() : null; return out;
    }
}
