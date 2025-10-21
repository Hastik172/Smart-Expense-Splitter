package com.example.expensesplitter.dto;

import java.math.BigDecimal;

public class ExpenseShareDto {
    public Long id;
    public Long expenseId;
    public Long userId;
    public BigDecimal shareAmount;
    public boolean isSettled;

    public ExpenseShareDto() {}
}
