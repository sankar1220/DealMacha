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
import com.dealmacha.model.AddressModel;
import com.dealmacha.model.RolesModel;
import com.dealmacha.model.UsersModel;
import com.dealmacha.util.CollectionTypeDescriptor;

@Component("rolesToRolesModelConverter")
public class RolesToRolesModelConverter implements Converter<Roles, RolesModel> {
    @Autowired
    private ObjectFactory<RolesModel> rolesModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public RolesModel convert(final Roles source) {
    	RolesModel rolesModel = rolesModelFactory.getObject();
        BeanUtils.copyProperties(source, rolesModel);

       /* if (CollectionUtils.isNotEmpty(source.getUsers())) {
            List<UsersModel> converted = (List<UsersModel>) conversionService.convert(source.getUsers(),
                    TypeDescriptor.forObject(source.getUsers()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), UsersModel.class));
            addressModel.getUsersModels().addAll(converted);
        }*/

        return rolesModel;
    }

    @Autowired
    public void setRolesModelFactory(final ObjectFactory<RolesModel> rolesModelFactory) {
        this.rolesModelFactory = rolesModelFactory;
    }

}