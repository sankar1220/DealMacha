package com.dealmacha.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dealmacha.dao.CashbackTransactionRepository;
import com.dealmacha.domain.CashbackTransaction;
import com.dealmacha.domain.Users;

@Component
public class CashbackTransactionService implements ICashbackTransactionService {
	@Autowired
	private CashbackTransactionRepository cashbackTransactionRepository;

	@Override
	public CashbackTransaction create(CashbackTransaction cashbackTransaction) {
		CashbackTransaction cashbackTrans=cashbackTransactionRepository.save(cashbackTransaction);
		/*CashbackTransaction cashbackTransaction1=new CashbackTransaction();		
		Set<CashbackTransaction> cashbackTrans=new HashSet<CashbackTransaction>();
		CashbackTransaction cashbackTransaction2=new CashbackTransaction();	
		cashbackTransaction2.setLongDescription(longDescription);
		if(cashbackTransaction2.getTransactionType()=="CREDIT"){
			cashbackTransaction2.setShortDescription("Credit successfully");
		}
		else{
			cashbackTransaction2.setShortDescription("Debit successfully");
		}
		cashbackTransaction1=cashbackTransactionRepository.save(cashbackTransaction2);
		
		return cashbackTransaction1;
	*/
		return null;
		
	}

	@Override
	public void deleteCashbackTransaction(String cashbackTransactionId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CashbackTransaction getCashbackTransaction(
			String cashbackTransactionId) {
		// TODO Auto-generated method stub
		return cashbackTransactionRepository.findOne(cashbackTransactionId);
	}

	@Override
	public List<CashbackTransaction> getAll() {
		List<CashbackTransaction> cashbackTransaction=(List<CashbackTransaction>)cashbackTransactionRepository.findAll();		
		List<CashbackTransaction>  cashbackTransactions=new ArrayList<CashbackTransaction>();		
		for(CashbackTransaction cbt:cashbackTransaction){
			CashbackTransaction ct=new CashbackTransaction();
			ct.setId(cbt.getId());
			ct.setDate(cbt.getDate());			
			ct.setLongDescription(cbt.getLongDescription());
			ct.setShortDescription(cbt.getShortDescription());
			ct.setTransactionType(cbt.getTransactionType());
			Users users=new Users();
			users.setId(cbt.getUsers().getId());
			users.setUserName(cbt.getUsers().getUserName());
			ct.setUsers(users);
			cashbackTransactions.add(ct);
			
			/*Set<Users> users=new HashSet<Users>();
			if(cbt.getUsers()!=null){
				for(Users user : cbt.getUsers()) {
					
				}
				Users usr=new Users();
				usr.setId(cbt.getUsers().getId());
				usr.setUserName(cbt.getUsers().getUserName());
				users.add(usr);
			}
			cashbackTransaction.setUsers(users);*/
		}
		
		
		return cashbackTransactions;
	}

	@Override
	public CashbackTransaction updateCashbackTransaction(
			CashbackTransaction cashbackTransaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CashbackTransaction> getUserCashbackTransaction(String usersId) {
		// TODO Auto-generated method stub
		return cashbackTransactionRepository.findUserCashbackTransaction(usersId);
	}

	

}
