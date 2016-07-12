package com.dealmacha.service;

import com.dealmacha.domain.Transaction;

import java.util.List;

public interface ITransactionService {
    Transaction create(Transaction transaction);

    void deleteTransaction(String transactionId);

    List<Transaction> getAll();

    List<Transaction> getDateTransaction(String dateId);

    Integer getMaxCode();

    List<Transaction> getMerchantTransaction(String merchantId);

    Transaction getTransaction(String transactionId);

    List<Transaction> getUsersTransaction(String usersId);

    Transaction updateTransaction(Transaction transaction);
  /*  List<Transaction> getAllTotalTransaction();*/
    List<Transaction> getFailTransactions();
}
