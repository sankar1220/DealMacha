package com.dealmacha.service;

import java.util.List;

import com.dealmacha.domain.MailConfig;
import com.dealmacha.domain.Roles;

public interface IRolesService {
	Roles create(Roles roles);

    void deleteRoles(String rolesId);

    /**
     * @param string
     * @return
     */
    Roles getRoles(String rolesId);

    List<Roles> getAll();



    Roles updateRoles(Roles roles);
}
