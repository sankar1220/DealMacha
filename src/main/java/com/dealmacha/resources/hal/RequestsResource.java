package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

@Relation(value = "requests", collectionRelation = "requests")
public class RequestsResource extends ResourceWithEmdedded {
	private String requestsId;
	private String requestTypesId;
	private String requestTypeName;
	private String userName;
	private String usersId;
	private String requestType;
	private String requestComments;
	private String requestStatus;
	private String createdDate;
	private String modifiedDate;
	private String amount;

	
	public String getRequestTypeName() {
		return requestTypeName;
	}

	public void setRequestTypeName(String requestTypeName) {
		this.requestTypeName = requestTypeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRequestTypesId() {
		return requestTypesId;
	}

	public void setRequestTypesId(String requestTypesId) {
		this.requestTypesId = requestTypesId;
	}

	public String getUsersId() {
		return usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestComments() {
		return requestComments;
	}

	public void setRequestComments(String requestComments) {
		this.requestComments = requestComments;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRequestsId() {
		return requestsId;
	}

	public void setRequestsId(String requestsId) {
		this.requestsId = requestsId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
