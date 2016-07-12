/**
 *
 */
package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author mohan
 *
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PayoomOfferContext implements IBusinessDelegateContext {

    private String all;
    private String payoomOfferId;

    /**
     * @return the all
     */
    public String getAll() {
        return all;
    }

    /**
     * @return the payoomOfferId
     */
    public String getPayoomOfferId() {
        return payoomOfferId;
    }

    /**
     * @param all
     *            the all to set
     */
    public void setAll(final String all) {
        this.all = all;
    }

    /**
     * @param payoomOfferId
     *            the payoomOfferId to set
     */
    public void setPayoomOfferId(final String payoomOfferId) {
        this.payoomOfferId = payoomOfferId;
    }

}
