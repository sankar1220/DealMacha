/**
 *
 */
package com.dealmacha.dao;

import com.dealmacha.domain.CmsSection;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Jay
 *
 */

public interface CmsSectionRepository extends PagingAndSortingRepository<CmsSection, Serializable> {

    /**
     * @return
     */
    @Query("SELECT cs.sectionName,cs FROM CmsSection cs")
    List<Object[]> findCmsSectionsAsMapWithSectionNameAsKey();

    /**
     * @param sectionName
     * @return
     */
    @Query("SELECT cs FROM CmsSection cs WHERE cs.sectionName=?1")
    CmsSection findSectionByName(String sectionName);

}
