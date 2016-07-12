/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.domain.MailConfig;

import java.util.List;

/**
 * @author arthvedi
 *
 */
public interface IMailConfigService {

    MailConfig create(MailConfig mailConfig);

    void deleteMailConfig(String mailConfigId);

    List<MailConfig> getAll();

    MailConfig getMailConfig(String mailConfigId);

    /**
     * @return
     */
    MailConfig getOrderPlacedMailConfig();

    /**
     * @return
     */
    MailConfig getUserAcitvationMailConfig();

    /**
     * @return
     */
    MailConfig getUserRegistrationMailConfig();

    MailConfig updateMailConfig(MailConfig mailConfig);

}
