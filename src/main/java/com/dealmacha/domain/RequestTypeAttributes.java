package com.dealmacha.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "request_type_attributes", catalog = "dealmacha_app")
public class RequestTypeAttributes extends AbstractDomain implements java.io.Serializable {

	private RequestTypes requestTypes;
	private String requestTypeAttribute;
	private String status;
	
	public RequestTypeAttributes() {
	}

	public RequestTypeAttributes(String id, RequestTypes requestTypes,String status, String requestTypeAttribute
			) {
		this.id = id;
		this.requestTypes = requestTypes;
		this.status = status;
		this.requestTypeAttribute = requestTypeAttribute;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "request_types_id")
	public RequestTypes getRequestTypes() {
		return requestTypes;
	}

	public void setRequestTypes(RequestTypes requestTypes) {
		this.requestTypes = requestTypes;
	}
	@Column(name = "request_type_attribute", nullable = false, length = 45)
	public String getRequestTypeAttribute() {
		return requestTypeAttribute;
	}

	public void setRequestTypeAttribute(String requestTypeAttribute) {
		this.requestTypeAttribute = requestTypeAttribute;
	}
	@Column(name = "status", length = 45)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
