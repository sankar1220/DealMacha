package com.dealmacha.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dealmacha.dao.AccountRepository;
import com.dealmacha.domain.Account;

@Component
public class AccountService implements IAccountService {

    private static final Logger LOGGER = Logger.getLogger(AccountService.class);
    @Autowired
    private AccountRepository accountRepository;
	@Override
	public Account create(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}
	@Override
	public void deleteAccount(String accountId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Account getAccount(String accountId) {
		// TODO Auto-generated method stub
		return accountRepository.findOne(accountId);
	}
	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return (List<Account>) accountRepository.findAll();
	}
	@Override
	public Account updateAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}
	@Override
	public List<Account> getUsersAccount(String usersId) {
		// TODO Auto-generated method stub
		return accountRepository.findUserAccount(usersId);
	}
    

}
