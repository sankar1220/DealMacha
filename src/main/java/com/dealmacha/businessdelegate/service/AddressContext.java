package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AddressContext implements IBusinessDelegateContext {
    private String addressId;
    private String all;

    public String getAddressId() {
        return addressId;
    }

    public String getAll() {
        return all;
    }

    public void setAddressId(final String addressId) {
        this.addressId = addressId;
    }

    public void setAll(final String all) {
        this.all = all;
    }

}
