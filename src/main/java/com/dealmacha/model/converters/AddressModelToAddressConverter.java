package com.dealmacha.model.converters;

import com.dealmacha.domain.Address;
import com.dealmacha.domain.UserAddress;
import com.dealmacha.model.AddressModel;
import com.dealmacha.util.CollectionTypeDescriptor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("addressModelToAddressConverter")
public class AddressModelToAddressConverter implements Converter<AddressModel, Address> {
    @Autowired
    private ObjectFactory<Address> addressFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public Address convert(final AddressModel source) {
        Address address = addressFactory.getObject();
        BeanUtils.copyProperties(source, address);

        if (CollectionUtils.isNotEmpty(source.getUserAddressModels())) {
            List<UserAddress> converted = (List<UserAddress>) conversionService.convert(source.getUserAddressModels(),
                    TypeDescriptor.forObject(source.getUserAddressModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), UserAddress.class));
            address.getUserAddress().addAll(converted);
        }

        return address;
    }

    @Autowired
    public void setAddressFactory(final ObjectFactory<Address> addressFactory) {
        this.addressFactory = addressFactory;
    }

}