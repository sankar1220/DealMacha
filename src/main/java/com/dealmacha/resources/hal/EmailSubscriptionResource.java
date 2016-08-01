package com.dealmacha.resources.hal;

import java.util.Date;

import org.springframework.hateoas.core.Relation;

@Relation(value = "emailSubscription", collectionRelation = "emailSubscription")
public class EmailSubscriptionResource extends ResourceWithEmdedded {

	private String emailSubscriptionId;
	private String userEmail;
	private String subscriptionStatus;
	private Date createdDate;
	private Date modifiedDate;

	public String getEmailSubscriptionId() {
		return emailSubscriptionId;
	}

	public void setEmailSubscriptionId(String emailSubscriptionId) {
		this.emailSubscriptionId = emailSubscriptionId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
