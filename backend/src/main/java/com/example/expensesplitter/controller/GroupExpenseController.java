package com.example.expensesplitter.controller;

import com.example.expensesplitter.model.GroupExpense;
import com.example.expensesplitter.repository.GroupExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-expenses")
public class GroupExpenseController {
    @Autowired
    private GroupExpenseRepository repository;

    @GetMapping
    public List<GroupExpense> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public GroupExpense add(@RequestBody GroupExpense expense) {
        expense.setCreatedAt(java.time.LocalDateTime.now());
        return repository.save(expense);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
