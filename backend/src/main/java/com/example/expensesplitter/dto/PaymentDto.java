package com.example.expensesplitter.dto;

import java.math.BigDecimal;

public class PaymentDto {
    public Long id;
    public Long fromUserId;
    public Long toUserId;
    public BigDecimal amount;
    public String currency;
    public Long referenceExpenseId;

    public PaymentDto() {}
}
