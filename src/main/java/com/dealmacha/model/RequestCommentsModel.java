package com.dealmacha.model;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.Requests;

@Component("requestCommentsModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestCommentsModel extends AbstractModel {
	private String comments;
	private String customerUserId;
	private String createdDate;
	private String adminUserId;
	private String requestsId;
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCustomerUserId() {
		return customerUserId;
	}
	public void setCustomerUserId(String customerUserId) {
		this.customerUserId = customerUserId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getAdminUserId() {
		return adminUserId;
	}
	public void setAdminUserId(String adminUserId) {
		this.adminUserId = adminUserId;
	}
	public String getRequestsId() {
		return requestsId;
	}
	public void setRequestsId(String requestsId) {
		this.requestsId = requestsId;
	}
	
}
