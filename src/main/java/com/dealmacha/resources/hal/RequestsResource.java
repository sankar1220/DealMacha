package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

@Relation(value = "requests", collectionRelation = "requests")
public class RequestsResource extends ResourceWithEmdedded {

    private String requestsId;
    private String usersId;
	private String requestType;
	private String createdDate;
	private String requestDescription;
	private String requestStatus;
	public String getRequestsId() {
		return requestsId;
	}
	public void setRequestsId(String requestsId) {
		this.requestsId = requestsId;
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getRequestDescription() {
		return requestDescription;
	}
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	
}
