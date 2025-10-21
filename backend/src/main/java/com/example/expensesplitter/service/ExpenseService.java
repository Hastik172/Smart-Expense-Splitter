package com.example.expensesplitter.service;

import com.example.expensesplitter.model.Expense;
import com.example.expensesplitter.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> listAll() {
        return expenseRepository.findAll();
    }

    public Expense create(Expense e) {
        return expenseRepository.save(e);
    }

    public Expense findById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }
}
