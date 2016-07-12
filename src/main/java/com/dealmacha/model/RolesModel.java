package com.dealmacha.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("rolesModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RolesModel extends AbstractModel {

    private String roleName;
    private String roleDetails;
    private String enable;
    private String usersId;

    public String getEnable() {
        return enable;
    }

    public String getRoleDetails() {
        return roleDetails;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setEnable(final String enable) {
        this.enable = enable;
    }

    public void setRoleDetails(final String roleDetails) {
        this.roleDetails = roleDetails;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public void setUsersId(final String usersId) {
        this.usersId = usersId;
    }

}
