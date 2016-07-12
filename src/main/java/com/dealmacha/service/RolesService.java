package com.dealmacha.service;

import com.dealmacha.dao.RolesRepository;
import com.dealmacha.domain.Roles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolesService implements IRolesService {
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public Roles create(final Roles roles) {
        // TODO Auto-generated method stub
        return rolesRepository.save(roles);
    }

    @Override
    public void deleteRoles(final String rolesId) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Roles> getAll() {
        // TODO Auto-generated method stub
        return (List<Roles>) rolesRepository.findAll();
    }

    @Override
    public Roles getRoles(final String rolesId) {
        // TODO Auto-generated method stub
        return rolesRepository.findOne(rolesId);
    }

    @Override
    public Roles updateRoles(final Roles roles) {
        // TODO Auto-generated method stub
        return rolesRepository.save(roles);
    }

}
