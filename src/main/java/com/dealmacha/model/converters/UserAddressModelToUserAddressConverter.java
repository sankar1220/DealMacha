package com.dealmacha.model.converters;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.Address;
import com.dealmacha.domain.UserAddress;
import com.dealmacha.domain.Users;
import com.dealmacha.model.AddressModel;
import com.dealmacha.model.UserAddressModel;
import com.dealmacha.util.CollectionTypeDescriptor;

@Component("userAddressModelToUserAddressConverter")
public class UserAddressModelToUserAddressConverter implements Converter<UserAddressModel, UserAddress> {
    @Autowired
    private ObjectFactory<UserAddress> userAddressFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public UserAddress convert(final UserAddressModel source) {
    	UserAddress userAddress= userAddressFactory.getObject();
        BeanUtils.copyProperties(source, userAddress);

/*        if (CollectionUtils.isNotEmpty(source.getUsersModels())) {
            List<Users> converted = (List<Users>) conversionService.convert(source.getUsersModels(),
                    TypeDescriptor.forObject(source.getUsersModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), Users.class));
            address.getUsers().addAll(converted);
        }*/

        return userAddress;
    }

    @Autowired
    public void setUserAdressFactory(final ObjectFactory<UserAddress> userAddressFactory) {
        this.userAddressFactory = userAddressFactory;
    }

}
