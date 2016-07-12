package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.Roles;
import com.dealmacha.domain.Users;
import com.dealmacha.model.RolesModel;
import com.dealmacha.service.RolesService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

@Service
public class RolesBusinessDelegate implements IBusinessDelegate<RolesModel, RolesContext, IKeyBuilder<String>, String> {
    Logger LOGGER = Logger.getLogger(RolesBusinessDelegate.class);
    @Autowired
    private RolesService rolesService;
    @Autowired
    private ConversionService conversionService;

    @Override
    public RolesModel create(RolesModel model) {
        Roles roles = new Roles();
        roles.setId(model.getId());
        roles.setEnable(model.getEnable());
        roles.setRoleDetails(model.getRoleDetails());
        roles.setRoleName(model.getRoleName());
        Users users = new Users();
        users.setId(model.getUsersId());
        roles.setUsers(users);
        roles = rolesService.create(roles);
        model = conversionService.convert(roles, RolesModel.class);
        return model;
    }

    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final RolesContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public RolesModel edit(final IKeyBuilder<String> keyBuilder, final RolesModel model) {
        Roles roles = rolesService.getRoles(keyBuilder.build().toString());
        roles.setEnable(model.getEnable());
        roles.setRoleDetails(model.getRoleDetails());
        roles.setRoleName(model.getRoleName());
        roles = rolesService.updateRoles(roles);
        model.setId(roles.getId());
        return model;
    }

    @Override
    public RolesModel getByKey(final IKeyBuilder<String> keyBuilder, final RolesContext context) {
        Roles roles = rolesService.getRoles(keyBuilder.build().toString());
        RolesModel rolesModel = conversionService.convert(roles, RolesModel.class);

        return rolesModel;
    }

    @Override
    public Collection<RolesModel> getCollection(final RolesContext context) {
        List<Roles> roles = new ArrayList<Roles>();
        if (context.getAll() != null) {
            roles = rolesService.getAll();
        }
        List<RolesModel> rolesModels = (List<RolesModel>) conversionService.convert(roles, TypeDescriptor.forObject(roles),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(RolesModel.class)));
        return rolesModels;
    }

}
