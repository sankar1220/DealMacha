package com.dealmacha.resources.hal;

import java.util.Date;

import org.springframework.hateoas.core.Relation;

import com.dealmacha.domain.Requests;

@Relation(value="requestComments",collectionRelation="requestComments")
public class RequestCommentsResource extends ResourceWithEmdedded{
private String requestCommentsId;

private String comments;
private String customerUserId;
private String createdDate;
private String adminUserId;
private String requestsId;
public String getRequestCommentsId() {
	return requestCommentsId;
}
public void setRequestCommentsId(String requestCommentsId) {
	this.requestCommentsId = requestCommentsId;
}
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
