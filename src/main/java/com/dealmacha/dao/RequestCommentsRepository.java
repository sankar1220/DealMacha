package com.dealmacha.dao;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dealmacha.domain.RequestComments;

public interface RequestCommentsRepository extends PagingAndSortingRepository<RequestComments, Serializable> {

}
