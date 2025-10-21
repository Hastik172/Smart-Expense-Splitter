package com.example.expensesplitter.repository;

import com.example.expensesplitter.model.PersonalExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalExpenseRepository extends JpaRepository<PersonalExpense, Long> {
}
