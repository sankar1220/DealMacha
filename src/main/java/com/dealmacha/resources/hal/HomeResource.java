package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

import com.dealmacha.domain.Users;

@Relation(value = "home", collectionRelation = "home")
public class HomeResource extends ResourceWithEmdedded {
	private String activatedUsers;
	private String totalTransactions;	
	private String commissionReceived;
	public String getActivatedUsers() {
		return activatedUsers;
	}
	public void setActivatedUsers(String activatedUsers) {
		this.activatedUsers = activatedUsers;
	}
	public String getTotalTransactions() {
		return totalTransactions;
	}
	public void setTotalTransactions(String totalTransactions) {
		this.totalTransactions = totalTransactions;
	}
	public String getCommissionReceived() {
		return commissionReceived;
	}
	public void setCommissionReceived(String commissionReceived) {
		this.commissionReceived = commissionReceived;
	}
	

}
