/**
 *
 */
package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author arthvedi5
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MerchantCategoryContext implements IBusinessDelegateContext {
    private String all;
    private String merchantCategoryId;

    public String getAll() {
        return all;
    }

    public String getMerchantCategoryId() {
        return merchantCategoryId;
    }

    public void setAll(final String all) {
        this.all = all;
    }

    public void setMerchantCategoryId(final String merchantCategoryId) {
        this.merchantCategoryId = merchantCategoryId;
    }

}
