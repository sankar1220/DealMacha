package com.dealmacha.dao;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dealmacha.domain.RequestTypes;

public interface RequestTypesRepository extends PagingAndSortingRepository<RequestTypes, Serializable> {

}
