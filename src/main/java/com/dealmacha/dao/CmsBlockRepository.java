/**
 *
 */
package com.dealmacha.dao;

import com.dealmacha.domain.CmsBlock;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Jay
 *
 */
public interface CmsBlockRepository extends PagingAndSortingRepository<CmsBlock, Serializable> {

    /**
     * @param blockName
     * @return
     */
    @Query("SELECT cb FROM CmsBlock cb WHERE cb.blockName = ?1")
    CmsBlock findCmsBlockByName(String blockName);

}
