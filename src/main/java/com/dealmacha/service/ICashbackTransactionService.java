package com.dealmacha.service;

import java.util.List;

import com.dealmacha.domain.CashbackTransaction;

public interface ICashbackTransactionService {
	CashbackTransaction create(CashbackTransaction cashbackTransaction);

    void deleteCashbackTransaction(String cashbackTransactionId);

    CashbackTransaction getCashbackTransaction(String cashbackTransactionId);

    List<CashbackTransaction> getAll();	  

    CashbackTransaction updateCashbackTransaction(CashbackTransaction cashbackTransaction);
    List<CashbackTransaction> getUserCashbackTransaction(String usersId);
}
