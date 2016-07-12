package com.dealmacha.model.converters;

import com.dealmacha.domain.UserAddress;
import com.dealmacha.model.UserAddressModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("userAddressToUserAddressModelConverter")
public class UserAddressToUserAddressModelConverter implements Converter<UserAddress, UserAddressModel> {
    @Autowired
    private ObjectFactory<UserAddressModel> userAddressModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public UserAddressModel convert(final UserAddress source) {
        UserAddressModel userAddressModel = userAddressModelFactory.getObject();
        BeanUtils.copyProperties(source, userAddressModel);

        /* if (CollectionUtils.isNotEmpty(source.getUsers())) {
             List<UsersModel> converted = (List<UsersModel>) conversionService.convert(source.getUsers(),
                     TypeDescriptor.forObject(source.getUsers()),
                     CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), UsersModel.class));
             addressModel.getUsersModels().addAll(converted);
         }
        */
        return userAddressModel;
    }

    @Autowired
    public void setUserAdressModelFactory(final ObjectFactory<UserAddressModel> userAddressModelFactory) {
        this.userAddressModelFactory = userAddressModelFactory;
    }

}
