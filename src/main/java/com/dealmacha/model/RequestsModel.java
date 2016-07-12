package com.dealmacha.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.dealmacha.domain.Users;

@Component("requestsModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestsModel extends AbstractModel {
	private String usersId;
	private String requestType;
	private String createdDate;
	private String requestDescription;
	private String requestStatus;
	private List<RequestCommentsModel> requestCommentsModels = new ArrayList<RequestCommentsModel>(0);
	
	public List<RequestCommentsModel> getRequestCommentsModels() {
		return requestCommentsModels;
	}
	public void setRequestCommentsModels(
			List<RequestCommentsModel> requestCommentsModels) {
		this.requestCommentsModels = requestCommentsModels;
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
