package com.dealmacha.model;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("cashbackTransactionModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CashbackTransactionModel extends AbstractModel {
	
	private String usersId;
	private String userName;
	 private String date;
private String transactionType;
private String shortDescription;
private String longDescription;
public String getUsersId() {
	return usersId;
}



public String getUserName() {
	return userName;
}



public void setUserName(String userName) {
	this.userName = userName;
}



public void setUsersId(String usersId) {
	this.usersId = usersId;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
public String getShortDescription() {
	return shortDescription;
}
public void setShortDescription(String shortDescription) {
	this.shortDescription = shortDescription;
}
public String getLongDescription() {
	return longDescription;
}
public void setLongDescription(String longDescription) {
	this.longDescription = longDescription;
}

}
