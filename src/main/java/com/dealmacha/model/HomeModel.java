package com.dealmacha.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.Users;

@Component("homeModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HomeModel extends AbstractModel {
	private String users;	
	private String activatedUsers;
	private String totalTransactions;
	private String dashboardData;
	
	private String commissionReceived;
	

	public String getDashboardData() {
		return dashboardData;
	}

	public void setDashboardData(String dashboardData) {
		this.dashboardData = dashboardData;
	}

	public String getCommissionReceived() {
		return commissionReceived;
	}

	public void setCommissionReceived(String commissionReceived) {
		this.commissionReceived = commissionReceived;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}
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
	
	

}
