package com.example.expensesplitter.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class ExpenseDto {
    public Long id;
    public Long groupId;
    public Long payerId;
    public BigDecimal amount;
    public String currency;
    public String description;
    public Instant occurredAt;

    public ExpenseDto() {}
}
