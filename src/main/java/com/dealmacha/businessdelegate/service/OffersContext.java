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
public class OffersContext implements IBusinessDelegateContext {
    private String offersId;
    private String all;

    /**
     * @return the all
     */
    public String getAll() {
        return all;
    }

    /**
     * @return the offersId
     */
    public String getOffersId() {
        return offersId;
    }

    /**
     * @param all
     *            the all to set
     */
    public void setAll(final String all) {
        this.all = all;
    }

    /**
     * @param offersId
     *            the offersId to set
     */
    public void setOffersId(final String offersId) {
        this.offersId = offersId;
    }

}
