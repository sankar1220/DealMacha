package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

@Relation(value = "requestTypeAttributes", collectionRelation = "requestTypeAttributes")
public class RequestTypeAttributesResource extends ResourceWithEmdedded {
	private String requestTypeAttributesId;
	private String requestTypesId;
	private String requestTypeName;
	private String requestTypeAttribute;
	private String status;

	public String getRequestTypeAttributesId() {
		return requestTypeAttributesId;
	}

	public void setRequestTypeAttributesId(String requestTypeAttributesId) {
		this.requestTypeAttributesId = requestTypeAttributesId;
	}

	public String getRequestTypesId() {
		return requestTypesId;
	}

	public void setRequestTypesId(String requestTypesId) {
		this.requestTypesId = requestTypesId;
	}

	public String getRequestTypeAttribute() {
		return requestTypeAttribute;
	}

	public void setRequestTypeAttribute(String requestTypeAttribute) {
		this.requestTypeAttribute = requestTypeAttribute;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestTypeName() {
		return requestTypeName;
	}

	public void setRequestTypeName(String requestTypeName) {
		this.requestTypeName = requestTypeName;
	}

}
