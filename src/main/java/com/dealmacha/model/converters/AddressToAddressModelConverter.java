package com.dealmacha.model.converters;

import com.dealmacha.domain.Address;
import com.dealmacha.model.AddressModel;
import com.dealmacha.model.UserAddressModel;
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

@Component("addressToAddressModelConverter")
public class AddressToAddressModelConverter implements Converter<Address, AddressModel> {
    @Autowired
    private ObjectFactory<AddressModel> addressModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public AddressModel convert(final Address source) {
        AddressModel addressModel = addressModelFactory.getObject();
        BeanUtils.copyProperties(source, addressModel);

        if (CollectionUtils.isNotEmpty(source.getUserAddress())) {
            List<UserAddressModel> converted = (List<UserAddressModel>) conversionService.convert(source.getUserAddress(),
                    TypeDescriptor.forObject(source.getUserAddress()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), UserAddressModel.class));
            addressModel.getUserAddressModels().addAll(converted);
        }
        return addressModel;
    }

    @Autowired
    public void setAddressModelFactory(final ObjectFactory<AddressModel> addressModelFactory) {
        this.addressModelFactory = addressModelFactory;
    }

}