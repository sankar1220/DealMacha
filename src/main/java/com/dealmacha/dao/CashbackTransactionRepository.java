package com.dealmacha.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dealmacha.domain.CashbackTransaction;
import com.dealmacha.domain.ClickTransaction;

public interface CashbackTransactionRepository extends PagingAndSortingRepository<CashbackTransaction, Serializable> {
@Query("select ct from CashbackTransaction ct where ct.users.id=?1")
	List<CashbackTransaction> findUserCashbackTransaction(String usersId);

}
