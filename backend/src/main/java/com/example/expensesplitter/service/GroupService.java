package com.example.expensesplitter.service;

import com.example.expensesplitter.model.ExpenseGroup;
import com.example.expensesplitter.repository.ExpenseGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final ExpenseGroupRepository repo;

    public GroupService(ExpenseGroupRepository repo) { this.repo = repo; }

    public List<ExpenseGroup> listAll() { return repo.findAll(); }

    public ExpenseGroup create(ExpenseGroup g) { return repo.save(g); }

    public ExpenseGroup findById(Long id) { return repo.findById(id).orElse(null); }
}
