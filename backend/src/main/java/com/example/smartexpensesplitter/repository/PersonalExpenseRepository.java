package com.example.smartexpensesplitter.repository;

import com.example.smartexpensesplitter.model.PersonalExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalExpenseRepository extends JpaRepository<PersonalExpense, Long> {}
