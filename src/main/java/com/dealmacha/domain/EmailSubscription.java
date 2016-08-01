/**
 *
 */
package com.dealmacha.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author varma
 *
 */
@Entity
@Table(name = "email_subscription", catalog = "dealmacha_app")
public class EmailSubscription extends AbstractDomain implements java.io.Serializable {

    private String userEmail;
    private String subscriptionStatus;
    private Date createdDate;
    private Date modifiedDate;

    public EmailSubscription() {

    }

    public EmailSubscription(final String id, final String userEmail, final String subscriptionStatus, final Date modifiedDate,
            final Date createdDate) {
        this.id = id;
        this.userEmail = userEmail;
        this.subscriptionStatus = subscriptionStatus;
        this.modifiedDate = modifiedDate;
        this.createdDate = createdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", length = 19)
    public Date getCreatedDate() {
        return createdDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date", length = 19)
    public Date getModifiedDate() {
        return modifiedDate;
    }

    @Column(name = "subscription_status")
    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    @Column(name = "user_email")
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
