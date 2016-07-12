/**
 *
 */
package com.dealmacha.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 *
 */
@Component("cmsSectionModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CmsSectionModel extends AbstractModel {

    private String sectionTitle;
    private String sectionName;
    private List<CmsBlockModel> cmsBlockModels = new ArrayList<CmsBlockModel>(0);

    /**
     * @return the cmsBlockModels
     */
    public List<CmsBlockModel> getCmsBlockModels() {
        return cmsBlockModels;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    /**
     * @param cmsBlockModels
     *            the cmsBlockModels to set
     */
    public void setCmsBlockModels(final List<CmsBlockModel> cmsBlockModels) {
        this.cmsBlockModels = cmsBlockModels;
    }

    public void setSectionName(final String sectionName) {
        this.sectionName = sectionName;
    }

    public void setSectionTitle(final String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

}
