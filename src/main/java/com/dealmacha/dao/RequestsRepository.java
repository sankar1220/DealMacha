package com.dealmacha.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dealmacha.domain.Requests;

public interface RequestsRepository  extends PagingAndSortingRepository<Requests, Serializable> {

	@Query("SELECT DISTINCT r from Requests r where r.users.id=?1")
	List<Requests> findRequestByUsers(String usersId);

}
