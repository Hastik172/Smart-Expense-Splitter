package com.example.expensesplitter.repository;

import com.example.expensesplitter.model.GroupExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupExpenseRepository extends JpaRepository<GroupExpense, Long> {
}
