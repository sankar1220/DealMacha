package com.dealmacha.dao;

import com.dealmacha.domain.UserAddress;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserAddressRepository extends PagingAndSortingRepository<UserAddress, Serializable> {

}
