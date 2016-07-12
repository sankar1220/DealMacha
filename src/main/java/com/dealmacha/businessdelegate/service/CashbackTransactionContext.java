package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CashbackTransactionContext implements IBusinessDelegateContext{
private String all;
private String cashbackTransactionId;
private String usersId;
public String getAll() {
	return all;
}
public void setAll(String all) {
	this.all = all;
}
public String getCashbackTransactionId() {
	return cashbackTransactionId;
}
public void setCashbackTransactionId(String cashbackTransactionId) {
	this.cashbackTransactionId = cashbackTransactionId;
}
public String getUsersId() {
	return usersId;
}
public void setUsersId(String usersId) {
	this.usersId = usersId;
}


}
