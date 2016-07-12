/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.domain.CmsSection;

import java.util.Map;

/**
 * @author Jay
 *
 */
public interface ICmsSectionService {

    /**
     * @return
     */
    CmsSection create(CmsSection cmsSection);

    /**
     * @return
     */
    Map<String, CmsSection> getAllSectionAsHashMap();

    /**
     * @param sectionName
     * @return
     */
    CmsSection getSectionByName(String sectionName);

    /**
     * @param cmsSection
     * @return
     */
    CmsSection updateCmsSection(CmsSection cmsSection);

}
