package com.dealmacha.service;

import java.util.List;

import com.dealmacha.domain.Account;
import com.dealmacha.domain.Address;

public interface IAccountService {
	Account create(Account account);

    void deleteAccount(String accountId);

    
    Account getAccount(String accountId);

    List<Account> getAll();

   

    Account updateAccount(Account account);

	List<Account> getUsersAccount(String usersId);

}
