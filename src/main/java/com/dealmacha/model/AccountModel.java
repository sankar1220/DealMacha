package com.dealmacha.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("accountModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AccountModel extends AbstractModel {
	private String usersId;
	private String cashbackAmount;
	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}
	public String getCashbackAmount() {
		return cashbackAmount;
	}
	public void setCashbackAmount(String cashbackAmount) {
		this.cashbackAmount = cashbackAmount;
	}
	

}
