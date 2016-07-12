package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.Address;
import com.dealmacha.domain.UserAddress;
import com.dealmacha.domain.Users;
import com.dealmacha.model.UserAddressModel;
import com.dealmacha.service.UserAddressService;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class UserAddressBusinessDelegate implements IBusinessDelegate<UserAddressModel, UserAddressContext, IKeyBuilder<String>, String> {
    Logger LOGGER = Logger.getLogger(UserAddressBusinessDelegate.class);
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private UserAddressService userAddressService;

    @Override
    public UserAddressModel create(UserAddressModel model) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(model.getId());
        Address address = new Address();
        address.setId(model.getAddressId());
        userAddress.setAddress(address);
        Users users = new Users();
        users.setId(model.getUsersId());
        userAddress.setUsers(users);
        userAddress = userAddressService.create(userAddress);
        model = conversionService.convert(userAddress, UserAddressModel.class);
        return model;
    }

    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final UserAddressContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public UserAddressModel edit(final IKeyBuilder<String> keyBuilder, final UserAddressModel model) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserAddressModel getByKey(final IKeyBuilder<String> keyBuilder, final UserAddressContext context) {
        UserAddress userAddress = userAddressService.getUserAddress(keyBuilder.build().toString());
        UserAddressModel userAddressModel = conversionService.convert(userAddress, UserAddressModel.class);

        return userAddressModel;
    }

    @Override
    public Collection<UserAddressModel> getCollection(final UserAddressContext context) {
        // TODO Auto-generated method stub
        return null;
    }

}
