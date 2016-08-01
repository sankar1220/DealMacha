package com.dealmacha.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dealmacha.domain.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Serializable> {
@Query("SELECT a FROM Account a WHERE a.users.id=?1")
	List<Account> findUserAccount(String usersId);


}
