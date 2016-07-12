package com.dealmacha.service;

import java.util.List;

import com.dealmacha.domain.Roles;
import com.dealmacha.domain.UserAddress;

public interface IUserAddressService {
	UserAddress create(UserAddress userAddress);

    void deleteUserAddress(String userAddressId);

    /**
     * @param string
     * @return
     */
    UserAddress getUserAddress(String userAddressId);

    List<UserAddress> getAll();



    UserAddress updateUserAddress(UserAddress userAddress);
}
