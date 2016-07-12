/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.dao.MailConfigRepository;
import com.dealmacha.domain.MailConfig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author arthvedi
 *
 */

@Component
public class MailConfigService implements IMailConfigService {

    @Autowired
    private MailConfigRepository mailConfigRepository;

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IMailConfigService#create(com.meat.domain.MailConfig)
     */
    @Override
    @Transactional
    public MailConfig create(final MailConfig mailConfig) {
        // TODO Auto-generated method stub
        return mailConfigRepository.save(mailConfig);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IMailConfigService#deleteMailConfig(java.lang.String)
     */
    @Override
    public void deleteMailConfig(final String mailConfigId) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IMailConfigService#getAll()
     */
    @Override
    public List<MailConfig> getAll() {
        List<MailConfig> mailConfig = new ArrayList<MailConfig>();
        mailConfig = (List<MailConfig>) mailConfigRepository.findAll();
        return mailConfig;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IMailConfigService#getMailConfig(java.lang.String)
     */
    @Override
    public MailConfig getMailConfig(final String mailConfigId) {
        // TODO Auto-generated method stub
        return mailConfigRepository.findOne(mailConfigId);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IMailConfigService#getOrderPlacedMailConfig()
     */
    @Override
    public MailConfig getOrderPlacedMailConfig() {
        MailConfig mailConfig = new MailConfig();
        String status = "ACTIVE";
        String attributeValue = "USERPLACEDORDER";
        String attributeName = "USERMAIL";
        mailConfig = mailConfigRepository.findUserlacedOrderMailConfig(status, attributeValue, attributeName);
        return mailConfig;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IMailConfigService#getUserAcitvationMailConfig()
     */
    @Override
    public MailConfig getUserAcitvationMailConfig() {
        MailConfig mailConfig = new MailConfig();
        String status = "ACTIVE";
        String attributeValue = "USERACTIVATION";
        String attributeName = "USERMAIL";
        mailConfig = mailConfigRepository.findUserRegistrationMailConfig(status, attributeValue, attributeName);
        return mailConfig;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IMailConfigService#getAllMailConfig()
     */
    @Override
    public MailConfig getUserRegistrationMailConfig() {
        MailConfig mailConfig = new MailConfig();
        String status = "ACTIVE";
        String attributeValue = "USERREGISTRATION";
        String attributeName = "USERMAIL";
        mailConfig = mailConfigRepository.findUserRegistrationMailConfig(status, attributeValue, attributeName);
        return mailConfig;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IMailConfigService#updateMailConfig(com.meat.domain.MailConfig)
     */
    @Override
    public MailConfig updateMailConfig(final MailConfig mailConfig) {
        // TODO Auto-generated method stub
        return mailConfigRepository.save(mailConfig);
    }

}
