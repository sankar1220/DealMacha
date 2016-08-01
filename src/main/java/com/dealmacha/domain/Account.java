package com.dealmacha.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account", catalog = "dealmacha_app")
public class Account extends AbstractDomain implements java.io.Serializable {
	private Users users;
	private BigDecimal cashbackAmount;
	 public Account() {
	    }
	 public Account(final Users users,final BigDecimal cashbackAmount) {
		 this.users=users;
		 this.cashbackAmount=cashbackAmount;
	    }
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "users_id", nullable = false)
	public Users getUsers() {
		return users;
	}
	public void setUsers(final Users users) {
		this.users = users;
	}
	@Column(name = "cashback_amount", nullable = false, precision = 7)
	public BigDecimal getCashbackAmount() {
		return cashbackAmount;
	}
	public void setCashbackAmount(final BigDecimal cashbackAmount) {
		this.cashbackAmount = cashbackAmount;
	}
	 
}
