/**
 *
 */
package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 *
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CmsSectionContext implements IBusinessDelegateContext {
    private String sectionTitle;
    private String sectionName;

    public String getSectionName() {
        return sectionName;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionName(final String sectionName) {
        this.sectionName = sectionName;
    }

    public void setSectionTitle(final String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

}
