package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

/**
 * @author arthvedi5
 *
 */
@Relation(value = "cashbackTransaction", collectionRelation = "cashbackTransaction")
public class CashbackTransactionResource extends ResourceWithEmdedded {
    private String cashbackTransactionId;
    private String usersId;
	 private String date;
private String transactionType;
private String shortDescription;
private String userName;
private String longDescription;
public String getCashbackTransactionId() {
	return cashbackTransactionId;
}



public String getUserName() {
	return userName;
}



public void setUserName(String userName) {
	this.userName = userName;
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
