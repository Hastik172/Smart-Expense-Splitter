package com.example.smartexpensesplitter.repository;

import com.example.smartexpensesplitter.model.GroupExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupExpenseRepository extends JpaRepository<GroupExpense, Long> {}
