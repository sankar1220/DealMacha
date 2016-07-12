package com.dealmacha.service;

import com.dealmacha.dao.UserAddressRepository;
import com.dealmacha.domain.UserAddress;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAddressService implements IUserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Override
    public UserAddress create(final UserAddress usrAddress) {
        UserAddress uAdd = new UserAddress();
        uAdd.setAddress(usrAddress.getAddress());
        uAdd.setUsers(usrAddress.getUsers());
        uAdd = userAddressRepository.save(usrAddress);
        return uAdd;
    }

    @Override
    public void deleteUserAddress(final String userAddressId) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<UserAddress> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserAddress getUserAddress(final String userAddressId) {
        // TODO Auto-generated method stub
        return userAddressRepository.findOne(userAddressId);
    }

    @Override
    public UserAddress updateUserAddress(final UserAddress userAddress) {
        // TODO Auto-generated method stub
        return userAddressRepository.save(userAddress);
    }

}
