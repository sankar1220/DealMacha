/**
 *
 */
package com.dealmacha.service;

import java.util.List;

import com.dealmacha.domain.EmailSubscription;

/**
 * @author varma
 *
 */
public interface IEmailSubscriptionService {

    EmailSubscription create(EmailSubscription emailSubscription);

    void deleteEmailSubscription(String emailSubscriptionId);

    List<EmailSubscription> getAll();

    EmailSubscription getEmailSubscription(String emailSubscriptionId);

    /**
     * @param userEmail
     * @return
     */
    EmailSubscription getEmailSubscriptionByEmail(String userEmail);

    /**
     * @return
     */
    List<EmailSubscription> getEmailSubscriptionSendsMail();

    EmailSubscription updateEmailSubscription(EmailSubscription emailSubscription);

}
