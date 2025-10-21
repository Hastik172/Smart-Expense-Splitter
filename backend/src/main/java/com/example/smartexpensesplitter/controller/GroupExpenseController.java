package com.example.smartexpensesplitter.controller;

import com.example.smartexpensesplitter.model.GroupExpense;
import com.example.smartexpensesplitter.repository.GroupExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/group-expenses")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupExpenseController {
    @Autowired
    private GroupExpenseRepository repo;

    @GetMapping
    public List<GroupExpense> getAll() { return repo.findAll(); }

    @PostMapping
    public GroupExpense add(@RequestBody GroupExpense e) { return repo.save(e); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
