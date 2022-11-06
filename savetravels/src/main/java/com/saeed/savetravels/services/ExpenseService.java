package com.saeed.savetravels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.saeed.savetravels.models.Expense;
import com.saeed.savetravels.repositories.ExpenseRepository;

@Service
public class ExpenseService {
	private final ExpenseRepository expenseRepository;

	public ExpenseService(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}
	public List<Expense> allExpenses() {
        return expenseRepository.findAll();
    }
	public Expense createExpense(Expense e) {
        return expenseRepository.save(e);
    }
	public Expense findExpense(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else {
            return null;
        }
    }
	public Expense updateExpense(Expense expense) {
   		return expenseRepository.save(expense);
   }
	public void deleteExpense(Long id) {
	   expenseRepository.deleteById(id);        
   }
}
