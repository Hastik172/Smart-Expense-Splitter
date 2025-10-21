package com.example.expensesplitter.service;

import com.example.expensesplitter.model.ExpenseShare;
import com.example.expensesplitter.repository.ExpenseShareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseShareService {
    private final ExpenseShareRepository repo;

    public ExpenseShareService(ExpenseShareRepository repo) { this.repo = repo; }

    public List<ExpenseShare> listAll() { return repo.findAll(); }

    public ExpenseShare create(ExpenseShare es) { return repo.save(es); }
}
