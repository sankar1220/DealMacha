/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.dao.CmsSectionRepository;
import com.dealmacha.domain.CmsSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 *
 */
@Component
public class CmsSectionService implements ICmsSectionService {

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsSectionService#create(com.dealmacha.domain.CmsSection)
     */
    @Autowired
    private CmsSectionRepository cmsSectionRepository;

    @Override
    public CmsSection create(final CmsSection cmsSection) {
        // TODO Auto-generated method stub

        return cmsSectionRepository.save(cmsSection);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsSectionService#getAllSectionAsHashMap()
     */
    @Override
    public Map<String, CmsSection> getAllSectionAsHashMap() {
        List<Object[]> cmsSections = cmsSectionRepository.findCmsSectionsAsMapWithSectionNameAsKey();
        Map<String, CmsSection> cmsSectionsMap = new HashMap<String, CmsSection>();
        for (Object[] o : cmsSections) {
            cmsSectionsMap.put(o[0].toString(), (CmsSection) o[1]);
        }
        return cmsSectionsMap;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsSectionService#getSectionByName(java.lang.String)
     */
    @Override
    public CmsSection getSectionByName(final String sectionName) {
        // TODO Auto-generated method stub
        return cmsSectionRepository.findSectionByName(sectionName);
    }

    @Override
    public CmsSection updateCmsSection(final CmsSection cmsSection) {
        // TODO Auto-generated method stub
        return cmsSectionRepository.save(cmsSection);
    }

}
