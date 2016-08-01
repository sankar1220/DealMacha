package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

@Relation(value = "users", collectionRelation = "users")
public class UsersResource extends ResourceWithEmdedded {
	private String usersId;
	private String userName;
	private String age;
	private String emailId;
	private String mobileNo;
	private String password;
	private String newPassword;
	private String userCode;
	private String status;
	private String userType;
	private String alternateMobileNo;
	private String emailStatus;
	private String authenticateStatus;
	private String confirmPassword;
	private String userEmailIdStatus;
	private String userMobileNoStatus;
	private String createdDate;
	private String userCount;

	public String getUsersId() {
		return usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAlternateMobileNo() {
		return alternateMobileNo;
	}

	public void setAlternateMobileNo(String alternateMobileNo) {
		this.alternateMobileNo = alternateMobileNo;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getAuthenticateStatus() {
		return authenticateStatus;
	}

	public void setAuthenticateStatus(String authenticateStatus) {
		this.authenticateStatus = authenticateStatus;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getUserEmailIdStatus() {
		return userEmailIdStatus;
	}

	public void setUserEmailIdStatus(String userEmailIdStatus) {
		this.userEmailIdStatus = userEmailIdStatus;
	}

	public String getUserMobileNoStatus() {
		return userMobileNoStatus;
	}

	public void setUserMobileNoStatus(String userMobileNoStatus) {
		this.userMobileNoStatus = userMobileNoStatus;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUserCount() {
		return userCount;
	}

	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}

}
