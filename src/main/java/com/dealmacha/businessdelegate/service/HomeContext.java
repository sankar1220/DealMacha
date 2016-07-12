package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HomeContext implements IBusinessDelegateContext {

	private String activatedUsers;
	private String all;
	private String dashboardData;
	private String totalTransactions;
	public String getActivatedUsers() {
		return activatedUsers;
	}
	public void setActivatedUsers(String activatedUsers) {
		this.activatedUsers = activatedUsers;
	}
	public String getAll() {
		return all;
	}
	public void setAll(String all) {
		this.all = all;
	}
	public String getDashboardData() {
		return dashboardData;
	}
	public void setDashboardData(String dashboardData) {
		this.dashboardData = dashboardData;
	}
	public String getTotalTransactions() {
		return totalTransactions;
	}
	public void setTotalTransactions(String totalTransactions) {
		this.totalTransactions = totalTransactions;
	}
	
	
}
