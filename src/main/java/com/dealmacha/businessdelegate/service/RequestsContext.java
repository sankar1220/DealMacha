package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestsContext implements IBusinessDelegateContext {
    private String requestsId;
    private String all;
    private String usersId;
	public String getRequestsId() {
		return requestsId;
	}
	public void setRequestsId(final String requestsId) {
		this.requestsId = requestsId;
	}
	public String getAll() {
		return all;
	}
	public void setAll(final String all) {
		this.all = all;
	}
	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}
    

}
