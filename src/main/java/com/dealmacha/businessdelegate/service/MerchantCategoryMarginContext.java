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
public class MerchantCategoryMarginContext implements IBusinessDelegateContext {
    private String all;
    private String merchantCategoryMarginId;

    public String getAll() {
        return all;
    }

    public String getMerchantCategoryMarginId() {
        return merchantCategoryMarginId;
    }

    public void setAll(final String all) {
        this.all = all;
    }

    public void setMerchantCategoryMarginId(final String merchantCategoryMarginId) {
        this.merchantCategoryMarginId = merchantCategoryMarginId;
    }

}
