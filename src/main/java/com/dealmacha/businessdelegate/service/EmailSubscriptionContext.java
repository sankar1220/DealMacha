package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component("emailSubscriptionContext")
public class EmailSubscriptionContext implements IBusinessDelegateContext {

	private String emailSubscriptionId;
	private String all;
	private String allEmails;
	

	public String getEmailSubscriptionId() {
		return emailSubscriptionId;
	}

	public void setEmailSubscriptionId(String emailSubscriptionId) {
		this.emailSubscriptionId = emailSubscriptionId;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	public String getAllEmails() {
		return allEmails;
	}

	public void setAllEmails(String allEmails) {
		this.allEmails = allEmails;
	}

}
