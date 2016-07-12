package com.dealmacha.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cashback_transaction", catalog = "dealmacha_app")
public class CashbackTransaction extends AbstractDomain implements java.io.Serializable {
	private Users users;
	 private Date date;
private String transactionType;
private String shortDescription;
private String longDescription;
public CashbackTransaction() {
}

public CashbackTransaction(final Users users,final Date date,final String transactionType,
		final String shortDescription,final String longDescription) {
	this.users=users;
	this.date=date;
	this.transactionType=transactionType;
	this.shortDescription=shortDescription;
	this.longDescription=longDescription;
}

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "users_id", nullable = false)
public Users getUsers() {
	return users;
}
public void setUsers(final Users users) {
	this.users = users;
}
@Column(name = "date", nullable = false, length = 19)
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
@Column(name = "transaction_type", nullable = false, length = 45)
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(final String transactionType) {
	this.transactionType = transactionType;
}
@Column(name = "short_description", length = 100)
public String getShortDescription() {
	return shortDescription;
}
public void setShortDescription(final String shortDescription) {
	this.shortDescription = shortDescription;
}
@Column(name = "long_description", length = 100)
public String getLongDescription() {
	return longDescription;
}
public void setLongDescription(final String longDescription) {
	this.longDescription = longDescription;
}

}
