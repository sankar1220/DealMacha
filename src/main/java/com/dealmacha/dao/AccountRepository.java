package com.dealmacha.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dealmacha.domain.Account;
import com.dealmacha.domain.Address;

public interface AccountRepository extends PagingAndSortingRepository<Account, Serializable> {
@Query("select a from Account a where a.users.id=?1")
	List<Account> findUserAccount(String usersId);

}
