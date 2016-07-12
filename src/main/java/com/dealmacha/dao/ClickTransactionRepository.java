package com.dealmacha.dao;

import com.dealmacha.domain.ClickTransaction;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClickTransactionRepository extends PagingAndSortingRepository<ClickTransaction, Serializable> {

}
