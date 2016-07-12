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
import com.dealmacha.domain.Roles;
import com.dealmacha.domain.Users;
import com.dealmacha.model.AddressModel;
import com.dealmacha.model.RolesModel;
import com.dealmacha.util.CollectionTypeDescriptor;

@Component("rolesModelToRolesConverter")
public class RolesModelToRolesConverter implements Converter<RolesModel, Roles> {
    @Autowired
    private ObjectFactory<Roles> rolesFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public Roles convert(final RolesModel source) {
    	Roles roles= rolesFactory.getObject();
        BeanUtils.copyProperties(source, roles);

 /*       if (CollectionUtils.isNotEmpty(source.getUsersModels())) {
            List<Users> converted = (List<Users>) conversionService.convert(source.getUsersModels(),
                    TypeDescriptor.forObject(source.getUsersModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), Users.class));
            address.getUsers().addAll(converted);
        }*/

        return roles;
    }

    @Autowired
    public void setRolesFactory(final ObjectFactory<Roles> rolesFactory) {
        this.rolesFactory = rolesFactory;
    }

}