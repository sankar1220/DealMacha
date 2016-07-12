package com.dealmacha.service;

import java.util.List;

import com.dealmacha.domain.ClickTransaction;
import com.dealmacha.domain.Users;

public interface IClickTransactionService {
	ClickTransaction create(ClickTransaction clickTransaction);

    void deleteClickTransaction(String clickTransactionId);

    ClickTransaction getClickTransaction(String clickTransactionId);

    List<ClickTransaction> getAll();	  

    ClickTransaction updateClickTransaction(ClickTransaction clickTransaction);
}
