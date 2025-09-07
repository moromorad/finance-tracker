package com.example.finance_tracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExpensesTest {

    @Test
    void totals_are_zero_when_empty() {
        Expenses expenses = new Expenses();

        assertEquals(0.0, expenses.total(), 0.0001);
        assertEquals(0.0, expenses.totalByCategory(Category.FOOD), 0.0001);
        assertTrue(expenses.totalsByCategory().isEmpty());
    }

    @Test
    void computes_total_and_totals_by_category() {
        Expenses expenses = new Expenses();

        expenses.add(new Expense("Milk", LocalDateTime.now(), 5.50, Category.FOOD));
        expenses.add(new Expense("Bus", LocalDateTime.now(), 2.75, Category.TRANSPORTATION));
        expenses.add(new Expense("Apples", LocalDateTime.now(), 3.25, Category.FOOD));
        expenses.add(new Expense("Movie", LocalDateTime.now(), 12.00, Category.ENTERTAINMENT));

        assertEquals(23.5, expenses.total(), 0.0001);
        assertEquals(8.75, expenses.totalByCategory(Category.FOOD), 0.0001);
        assertEquals(2.75, expenses.totalByCategory(Category.TRANSPORTATION), 0.0001);
        assertEquals(12.0, expenses.totalByCategory(Category.ENTERTAINMENT), 0.0001);

        Map<Category, Double> totals = expenses.totalsByCategory();
        assertEquals(3, totals.size());
        assertEquals(8.75, totals.get(Category.FOOD), 0.0001);
        assertEquals(2.75, totals.get(Category.TRANSPORTATION), 0.0001);
        assertEquals(12.0, totals.get(Category.ENTERTAINMENT), 0.0001);
    }

    @Test
    void grouping_includes_null_category() {
        Expenses expenses = new Expenses();

        Expense uncategorized = new Expense("Misc", LocalDateTime.now(), 4.00);
        expenses.add(uncategorized);

        assertEquals(4.00, expenses.total(), 0.0001);
        assertEquals(0.0, expenses.totalByCategory(Category.HOUSING), 0.0001);

        Map<Category, Double> totals = expenses.totalsByCategory();
        assertEquals(1, totals.size());
        assertTrue(totals.containsKey(Category.OTHER));
        assertEquals(4.00, totals.get(Category.OTHER), 0.0001);
    }

    @Test
    void remove_expense_updates_totals() {
        Expenses expenses = new Expenses();
        Expense e1 = new Expense("Milk", LocalDateTime.now(), 5.0, Category.FOOD);
        Expense e2 = new Expense("Housing", LocalDateTime.now(), 1000.0, Category.HOUSING);
        expenses.add(e1);
        expenses.add(e2);

        assertEquals(1005.0, expenses.total(), 0.0001);
        assertTrue(expenses.remove(e1));
        assertEquals(1000.0, expenses.total(), 0.0001);
        assertEquals(0.0, expenses.totalByCategory(Category.FOOD), 0.0001);
        assertEquals(1000.0, expenses.totalByCategory(Category.HOUSING), 0.0001);
    }

    @Test
    void getAll_is_unmodifiable() {
        Expenses expenses = new Expenses();
        expenses.add(new Expense("Milk", LocalDateTime.now(), 5.0, Category.FOOD));

        assertThrows(UnsupportedOperationException.class, () -> expenses.getAll().add(
                new Expense("Apples", LocalDateTime.now(), 3.0, Category.FOOD)
        ));
    }
}
