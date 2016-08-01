/**
 *
 */
package com.dealmacha.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dealmacha.dao.EmailSubscriptionRepository;
import com.dealmacha.domain.EmailSubscription;
import com.dealmacha.domain.MailConfig;
import com.dealmacha.mail.Mail;

/**
 * @author varma
 *
 */
@Component
public class EmailSubscriptionService implements IEmailSubscriptionService {

    @Autowired
    private EmailSubscriptionRepository emailSubscriptionRepository;

    @Autowired
    private IMailService mailService;
    @Autowired
    private IMailConfigService mailConfigService;
    @Value("${mail.from}")
    private String mailFrom;
    @Value("${mail.username}")
    private String mailUserName;
    @Value("${url}")
    private String url;

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IEmailSubscriptionService#create(com.meat.domain.EmailSubscription)
     */
    @Override
    public EmailSubscription create(final EmailSubscription emailSubscription) {
        EmailSubscription emailSub = new EmailSubscription();
        emailSub.setUserEmail(emailSubscription.getUserEmail());
        emailSub.setSubscriptionStatus("SUBSCRIBED");
        emailSub.setCreatedDate(new Date());
        emailSub = emailSubscriptionRepository.save(emailSub);
        return emailSub;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IEmailSubscriptionService#deleteEmailSubscription(java.lang.String)
     */
    @Override
    public void deleteEmailSubscription(final String emailSubscriptionId) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IEmailSubscriptionService#getAll()
     */
    @Override
    public List<EmailSubscription> getAll() {
        // TODO Auto-generated method stub
        return (List<EmailSubscription>) emailSubscriptionRepository.findAll();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IEmailSubscriptionService#getEmailSubscription(java.lang.String)
     */
    @Override
    public EmailSubscription getEmailSubscription(final String emailSubscriptionId) {
        // TODO Auto-generated method stub
        return emailSubscriptionRepository.findOne(emailSubscriptionId);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IEmailSubscriptionService#getEmailSubscriptionByEmail(java.lang.String)
     */
    @Override
    public EmailSubscription getEmailSubscriptionByEmail(final String userEmail) {
        // TODO Auto-generated method stub
        return emailSubscriptionRepository.findByEmailId(userEmail);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IEmailSubscriptionService#getEmailSubscriptionSendsMail()
     */
    @Override
    public List<EmailSubscription> getEmailSubscriptionSendsMail() {
        List<EmailSubscription> emailSubscriptions = emailSubscriptionRepository.findAllSubscribedUserEmails();

      /*  MailConfig mcfgs = mailConfigService.getUserSubscriptinMailConfig();
        if (mcfgs != null) {
            Mail mail = new Mail();
            mail.setMailFrom(mailFrom);
            mail.setMailSubject("Account Activation Request");
            emailSubscriptions = mailService.sendUserSubscribedMail(mail, emailSubscriptions);
        }*/
        return emailSubscriptions;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IEmailSubscriptionService#updateEmailSubscription(com.meat.domain.EmailSubscription)
     */
    @Override
    public EmailSubscription updateEmailSubscription(final EmailSubscription emailSubscription) {
        // TODO Auto-generated method stub
        return emailSubscriptionRepository.save(emailSubscription);
    }

}
