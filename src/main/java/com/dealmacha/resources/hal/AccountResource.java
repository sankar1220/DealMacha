package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

@Relation(value = "account", collectionRelation = "account")
public class AccountResource extends ResourceWithEmdedded {

    private String accountId;
    private String usersId;
	private String cashbackAmount;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
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
