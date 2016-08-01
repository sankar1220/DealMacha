package com.dealmacha.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("requestTypesModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestTypesModel extends AbstractModel {

	private String requestType;
	private String createdDate;
	private String status;
	private String requestDescription;
	private List<RequestsModel> requestsModels = new ArrayList<RequestsModel>(0);
	private List<RequestTypeAttributesModel> requestTypeAttributesModels = new ArrayList<RequestTypeAttributesModel>(0);

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

	public List<RequestsModel> getRequestsModels() {
		return requestsModels;
	}

	public void setRequestsModels(List<RequestsModel> requestsModels) {
		this.requestsModels = requestsModels;
	}

	public List<RequestTypeAttributesModel> getRequestTypeAttributesModels() {
		return requestTypeAttributesModels;
	}

	public void setRequestTypeAttributesModels(List<RequestTypeAttributesModel> requestTypeAttributesModels) {
		this.requestTypeAttributesModels = requestTypeAttributesModels;
	}

}
