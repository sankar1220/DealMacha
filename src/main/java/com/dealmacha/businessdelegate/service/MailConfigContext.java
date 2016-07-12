/**
 *
 */
package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author arthvedi
 *
 */

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component("mailConfigContext")
public class MailConfigContext implements IBusinessDelegateContext {

    private String all;
    private String mailConfigId;

    public String getAll() {
        return all;
    }

    public void setAll(final String all) {
        this.all = all;
    }

    /**
     * @return the mailConfigId
     */
    public String getMailConfigId() {
        return mailConfigId;
    }

    /**
     * @param mailConfigId the mailConfigId to set
     */
    public void setMailConfigId(String mailConfigId) {
        this.mailConfigId = mailConfigId;
    }

}
