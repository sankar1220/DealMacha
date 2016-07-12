package com.dealmacha.dao;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dealmacha.domain.Account;
import com.dealmacha.domain.Requests;

public interface RequestsRepository  extends PagingAndSortingRepository<Requests, Serializable> {

}
