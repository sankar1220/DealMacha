package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClickTransactionContext implements IBusinessDelegateContext{
private String all;
private String clickTransactionId;

	public String getClickTransactionId() {
	return clickTransactionId;
}

public void setClickTransactionId(String clickTransactionId) {
	this.clickTransactionId = clickTransactionId;
}

	public String getAll() {
	return all;
}

	public void setAll(String all) {
		this.all = all;
	}

	
	
	

}
