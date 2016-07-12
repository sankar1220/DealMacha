package com.dealmacha.businessdelegate.service;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MerchantContext implements IBusinessDelegateContext{
private String merchantId;
private String all;

public String getAll() {
	return all;
}

public void setAll(String all) {
	this.all = all;
}

public String getMerchantId() {
	return merchantId;
}

public void setMerchantId(String merchantId) {
	this.merchantId = merchantId;
}



}
