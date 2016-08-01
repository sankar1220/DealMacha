package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

@Relation(value = "requestTypes", collectionRelation = "requestTypes")
public class RequestTypesResource extends ResourceWithEmdedded {

	private String requestTypesId;
	private String requestType;
	private String createdDate;
	private String status;
	private String requestDescription;


	public String getRequestTypesId() {
		return requestTypesId;
	}

	public void setRequestTypesId(String requestTypesId) {
		this.requestTypesId = requestTypesId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestDescription() {
		return requestDescription;
	}

	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}

}
