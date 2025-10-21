package com.example.expensesplitter.service;

import com.example.expensesplitter.model.Payment;
import com.example.expensesplitter.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository repo;

    public PaymentService(PaymentRepository repo) { this.repo = repo; }

    public List<Payment> listAll() { return repo.findAll(); }

    public Payment create(Payment p) { return repo.save(p); }
}
