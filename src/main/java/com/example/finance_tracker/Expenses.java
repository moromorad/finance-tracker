package com.example.finance_tracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Aggregate for managing a list of {@link Expense} and computing summaries.
 */
public class Expenses {
    private final List<Expense> expenses = new ArrayList<>();

    public void add(Expense expense) {
        if (expense != null) {
            expenses.add(expense);
        }
    }

    public boolean remove(Expense expense) {
        return expenses.remove(expense);
    }

    public List<Expense> getAll() {
        return Collections.unmodifiableList(expenses);
    }

    /** Total of all expenses. */
    public double total() {
        return expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    /** Total filtered by a given {@link Category}. */
    public double totalByCategory(Category cat) {
        if (cat == null) return 0.0;
        return expenses.stream()
                .filter(e -> cat.equals(e.getCategory()))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    /** Map of totals grouped by {@link Category}.
     *  Null categories are grouped under {@link Category#OTHER} for consistency. */
    public Map<Category, Double> totalsByCategory() {
        return expenses.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getCategory() == null ? Category.OTHER : e.getCategory(),
                        Collectors.summingDouble(Expense::getAmount)
                ));
    }
}
