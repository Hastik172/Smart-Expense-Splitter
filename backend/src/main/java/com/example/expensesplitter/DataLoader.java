package com.example.expensesplitter;

import com.example.expensesplitter.model.Expense;
import com.example.expensesplitter.model.User;
import com.example.expensesplitter.service.ExpenseService;
import com.example.expensesplitter.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserService userService;
    private final ExpenseService expenseService;

    public DataLoader(UserService userService, ExpenseService expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.listAll().isEmpty()) {
            User a = new User(); a.setName("Alice"); a.setEmail("alice@example.com"); userService.create(a);
            User b = new User(); b.setName("Bob"); b.setEmail("bob@example.com"); userService.create(b);

            Expense e1 = new Expense(); e1.setAmount(new BigDecimal("120.00")); e1.setCurrency("USD"); e1.setPayer(a);
            expenseService.create(e1);
        }
    }
}
