/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.dao.CmsPostsRepository;
import com.dealmacha.domain.CmsPosts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 *
 */
@Component
public class CmsPostsService implements ICmsPostsService {

    @Autowired
    private CmsPostsRepository cmsPostsRepository;
    @Autowired
    private ICmsPostsImagesService cmsPostsImagesService;

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsPostsService#create(com.dealmacha.domain.CmsPosts)
     */
    @Override
    public CmsPosts create(final CmsPosts cmsPosts) {
        // TODO Auto-generated method stub
        return cmsPostsRepository.save(cmsPosts);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsPostsService#delete(com.dealmacha.domain.CmsPosts)
     */
    @Override
    public void delete(final CmsPosts cmsPosts) {

        cmsPostsImagesService.deleteCmsPostsImagesByPost(cmsPosts.getId());
        cmsPostsRepository.delete(cmsPosts);

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsPostsService#edit(com.dealmacha.domain.CmsPosts)
     */
    @Override
    public CmsPosts edit(final CmsPosts cmsPosts) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsPostsService#getCmsPosts(java.lang.String)
     */
    @Override
    public CmsPosts getCmsPosts(final String postsId) {
        // TODO Auto-generated method stub
        return cmsPostsRepository.findOne(postsId);
    }

}
