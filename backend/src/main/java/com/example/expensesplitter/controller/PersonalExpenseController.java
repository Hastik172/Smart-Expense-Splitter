package com.example.expensesplitter.controller;

import com.example.expensesplitter.model.PersonalExpense;
import com.example.expensesplitter.repository.PersonalExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal-expenses")
public class PersonalExpenseController {
    @Autowired
    private PersonalExpenseRepository repository;

    @GetMapping
    public List<PersonalExpense> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public PersonalExpense add(@RequestBody PersonalExpense expense) {
        expense.setCreatedAt(java.time.LocalDateTime.now());
        return repository.save(expense);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
