/**
 *
 */
package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

/**
 * @author Jay
 *
 */
@Relation(value = "cmsSection", collectionRelation = "cmsSection")
public class CmsSectionResource extends ResourceWithEmdedded {

    private String cmsSectionId;
    private String sectionTitle;
    private String sectionName;

    public String getCmsSectionId() {
        return cmsSectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setCmsSectionId(final String cmsSectionId) {
        this.cmsSectionId = cmsSectionId;
    }

    public void setSectionName(final String sectionName) {
        this.sectionName = sectionName;
    }

    public void setSectionTitle(final String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

}
