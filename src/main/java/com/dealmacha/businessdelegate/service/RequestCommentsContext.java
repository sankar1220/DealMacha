package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestCommentsContext implements IBusinessDelegateContext {
	private String all;
	private String requestCommentsId;
	
	public String getRequestCommentsId() {
		return requestCommentsId;
	}

	public void setRequestCommentsId(String requestCommentsId) {
		this.requestCommentsId = requestCommentsId;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}
}