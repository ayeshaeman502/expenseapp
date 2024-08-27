package com.example.expensetracker;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensetracker.database.Expense;
import com.example.expensetracker.database.ExpenseDao;
import com.example.expensetracker.database.ExpenseDatabase;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {

    private final ExpenseDao expenseDao;
    private final LiveData<List<Expense>> allExpenses;
    private final LiveData<Double> totalExpense;

    public ExpenseViewModel(Application application) {
        super(application);
        ExpenseDatabase db = ExpenseDatabase.getDatabase(application);
        expenseDao = db.expenseDao();
        allExpenses = expenseDao.getAllExpenses();
        totalExpense = expenseDao.getTotalExpense();
    }

    public LiveData<List<Expense>> getAllExpenses() {
        return allExpenses;
    }

    public void insert(Expense expense) {
        ExpenseDatabase.databaseWriteExecutor.execute(() -> expenseDao.insert(expense));
    }

    public void delete(Expense expense) {
        ExpenseDatabase.databaseWriteExecutor.execute(() -> expenseDao.delete(expense));
    }

    public LiveData<Double> getTotalExpense() {
        return totalExpense;
    }
}
