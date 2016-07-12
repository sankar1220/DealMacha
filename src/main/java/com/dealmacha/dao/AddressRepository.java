package com.dealmacha.dao;

import com.dealmacha.domain.Address;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends PagingAndSortingRepository<Address, Serializable> {

}
