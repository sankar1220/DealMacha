package com.dealmacha.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request_comments", catalog = "dealmacha_app")
public class RequestComments extends AbstractDomain implements java.io.Serializable {
	private String comments;
	private String customerUserId;
	private Date createdDate;
	private String adminUserId;
	private Requests requests;
	public RequestComments() {
    }
 public RequestComments(final String comments,final String customerUserId,final  Date createdDate,final String adminUserId,
		 final Requests requests) {
	 this.comments=comments;
	 this.createdDate=createdDate;
	 this.customerUserId=customerUserId;
	 this.adminUserId=adminUserId;
	 this.requests=requests;	 
    }
 @Column(name = "comments", length = 100)
public String getComments() {
	return comments;
}
public void setComments(String comments) {
	this.comments = comments;
}
@Column(name = "customer_user_id",  length = 100)
public String getCustomerUserId() {
	return customerUserId;
}
public void setCustomerUserId(String customerUserId) {
	this.customerUserId = customerUserId;
}
@Column(name = "created", nullable = false, length = 19)
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
@Column(name = "admin_user_id", length = 100)
public String getAdminUserId() {
	return adminUserId;
}
public void setAdminUserId(String adminUserId) {
	this.adminUserId = adminUserId;
}
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "requests_id", nullable = false)
public Requests getRequests() {
	return requests;
}
public void setRequests(Requests requests) {
	this.requests = requests;
}

 
}
