package com.dealmacha.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("usersModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UsersModel extends AbstractModel {
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
    private List<AccountModel> accountModels= new ArrayList<AccountModel>(0);
    private List<RolesModel> rolesModels = new ArrayList<RolesModel>(0);
    private List<RequestsModel> requestsModels = new ArrayList<RequestsModel>(0);
    private List<TransactionModel> transactionModels = new ArrayList<TransactionModel>(0);
    private List<UserAddressModel> userAddressModel = new ArrayList<UserAddressModel>(0);
    private List<ClickTransactionModel> clickTransactionModels = new ArrayList<ClickTransactionModel>(0);
    private List<CashbackTransactionModel> cashbackTransactionModels = new ArrayList<CashbackTransactionModel>(0);

    public String getAge() {
        return age;
    }

    public List<AccountModel> getAccountModels() {
		return accountModels;
	}

	public void setAccountModels(List<AccountModel> accountModels) {
		this.accountModels = accountModels;
	}

	/**
     * @return the alternateMobileNo
     */
    public String getAlternateMobileNo() {
        return alternateMobileNo;
    }

    public String getAuthenticateStatus() {
        return authenticateStatus;
    }

    public List<ClickTransactionModel> getClickTransactionModels() {
        return clickTransactionModels;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    public String getPassword() {
        return password;
    }

    public List<RolesModel> getRolesModels() {
        return rolesModels;
    }

    public String getStatus() {
        return status;
    }

    public List<TransactionModel> getTransactionModels() {
        return transactionModels;
    }

    public List<UserAddressModel> getUserAddressModel() {
        return userAddressModel;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getUserCount() {
        return userCount;
    }

    public String getUserEmailIdStatus() {
        return userEmailIdStatus;
    }

    public String getUserMobileNoStatus() {
        return userMobileNoStatus;
    }

    public String getUserName() {
        return userName;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    public void setAge(final String age) {
        this.age = age;
    }

    /**
     * @param alternateMobileNo
     *            the alternateMobileNo to set
     */
    public void setAlternateMobileNo(final String alternateMobileNo) {
        this.alternateMobileNo = alternateMobileNo;
    }

    public void setAuthenticateStatus(final String authenticateStatus) {
        this.authenticateStatus = authenticateStatus;
    }

    public void setClickTransactionModels(final List<ClickTransactionModel> clickTransactionModels) {
        this.clickTransactionModels = clickTransactionModels;
    }

    /**
     * @param confirmPassword
     *            the confirmPassword to set
     */
    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }

    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }

    public void setEmailStatus(final String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public void setMobileNo(final String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @param newPassword
     *            the newPassword to set
     */
    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setRolesModels(final List<RolesModel> rolesModels) {
        this.rolesModels = rolesModels;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setTransactionModels(final List<TransactionModel> transactionModels) {
        this.transactionModels = transactionModels;
    }

    public void setUserAddressModel(final List<UserAddressModel> userAddressModel) {
        this.userAddressModel = userAddressModel;
    }

    public void setUserCode(final String userCode) {
        this.userCode = userCode;
    }

    public void setUserCount(final String userCount) {
        this.userCount = userCount;
    }

    public void setUserEmailIdStatus(final String userEmailIdStatus) {
        this.userEmailIdStatus = userEmailIdStatus;
    }

    public void setUserMobileNoStatus(final String userMobileNoStatus) {
        this.userMobileNoStatus = userMobileNoStatus;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * @param userType
     *            the userType to set
     */
    public void setUserType(final String userType) {
        this.userType = userType;
    }

	public List<CashbackTransactionModel> getCashbackTransactionModels() {
		return cashbackTransactionModels;
	}

	public void setCashbackTransactionModels(
			List<CashbackTransactionModel> cashbackTransactionModels) {
		this.cashbackTransactionModels = cashbackTransactionModels;
	}

	public List<RequestsModel> getRequestsModels() {
		return requestsModels;
	}

	public void setRequestsModels(List<RequestsModel> requestsModels) {
		this.requestsModels = requestsModels;
	}

}
