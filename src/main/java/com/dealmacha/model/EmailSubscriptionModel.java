/**
 *
 */
package com.dealmacha.model;

import java.util.Date;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author varma
 *
 */
@Component("emailSubscriptionModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmailSubscriptionModel extends AbstractModel {

    private String userEmail;
    private String subscriptionStatus;
    private Date createdDate;
    private Date modifiedDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setSubscriptionStatus(final String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    public void setUserEmail(final String userEmail) {
        this.userEmail = userEmail;
    }

}
