package com.solvd.OnlineShopping.dao;

import java.util.List;

import com.solvd.OnlineShopping.model.Transaction;
import com.solvd.OnlineShopping.model.enums.TransactionStatus;


public interface ITransactionDAO extends IBaseDAO<Transaction> {

		List<Transaction> getTranactionsByStatus(TransactionStatus s); 
}
