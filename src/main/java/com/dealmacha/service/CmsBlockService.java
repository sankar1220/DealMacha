/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.dao.CmsBlockRepository;
import com.dealmacha.domain.CmsBlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Naveen-PC
 *
 */
@Component
public class CmsBlockService implements ICmsBlockService {

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsBlockService#create(com.dealmacha.domain.CmsBlock)
     */
    @Autowired
    private CmsBlockRepository cmsBlockRepository;

    @Override
    public CmsBlock create(final CmsBlock cmsBlock) {
        // TODO Auto-generated method stub

        return cmsBlockRepository.save(cmsBlock);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsBlockService#getCmsBlockByName(java.lang.String)
     */
    @Override
    public CmsBlock getCmsBlockByName(final String blockName) {
        // TODO Auto-generated method stub
        return cmsBlockRepository.findCmsBlockByName(blockName);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsBlockService#updateCmsBlock(com.dealmacha.domain.CmsBlock)
     */
    @Override
    public CmsBlock updateCmsBlock(final CmsBlock cmsBlock) {
        // TODO Auto-generated method stub
        return cmsBlockRepository.save(cmsBlock);
    }

}
