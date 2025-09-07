package com.example.finance_tracker;

import java.time.LocalDateTime;

public class Expense {
    private String name;
    private LocalDateTime dateTime;
    private double amount;
    private Category category;

    public Expense(String name, LocalDateTime dateTime, double amount) {
        this.amount = amount;
        this.name = name;
        this.dateTime = dateTime;
    }

    public Expense(String name, LocalDateTime dateTime, double amount, Category category) {
        this(name, dateTime, amount);
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
