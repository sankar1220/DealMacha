/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.domain.CmsBlock;

/**
 * @author Naveen-PC
 *
 */
public interface ICmsBlockService {

    /**
     *
     */
    CmsBlock create(CmsBlock cmsBlock);

    /**
     * @param blockName
     * @return
     */
    CmsBlock getCmsBlockByName(String blockName);

    /**
     * @param cmsBlock
     * @return
     */
    CmsBlock updateCmsBlock(CmsBlock cmsBlock);

}
