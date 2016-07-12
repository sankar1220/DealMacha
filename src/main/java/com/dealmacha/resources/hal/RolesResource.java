package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

import com.dealmacha.domain.Users;

@Relation(value="roles",collectionRelation="roles")
public class RolesResource extends ResourceWithEmdedded{
private String rolesId;
private String roleName;
private String roleDetails;
private String enable;
private Users usersId;
public String getRolesId() {
	return rolesId;
}
public void setRolesId(String rolesId) {
	this.rolesId = rolesId;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public String getRoleDetails() {
	return roleDetails;
}
public void setRoleDetails(String roleDetails) {
	this.roleDetails = roleDetails;
}
public String getEnable() {
	return enable;
}
public void setEnable(String enable) {
	this.enable = enable;
}
public Users getUsersId() {
	return usersId;
}
public void setUsersId(Users usersId) {
	this.usersId = usersId;
}

}
