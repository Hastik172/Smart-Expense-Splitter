package com.example.smartexpensesplitter.controller;

import com.example.smartexpensesplitter.model.PersonalExpense;
import com.example.smartexpensesplitter.repository.PersonalExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/personal-expenses")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonalExpenseController {
    @Autowired
    private PersonalExpenseRepository repo;

    @GetMapping
    public List<PersonalExpense> getAll() { return repo.findAll(); }

    @PostMapping
    public PersonalExpense add(@RequestBody PersonalExpense e) { return repo.save(e); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
