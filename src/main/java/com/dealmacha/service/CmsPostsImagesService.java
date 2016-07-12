/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.dao.CmsPostsImagesRepository;
import com.dealmacha.domain.CmsPostsImages;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jay
 *
 */
@Component
public class CmsPostsImagesService implements ICmsPostsImagesService {

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsPostsImagesService#create(com.dealmacha.domain.CmsPostsImages)
     */
    @Autowired
    private CmsPostsImagesRepository cmsPostsImagesRepository;

    @Override
    public CmsPostsImages create(final CmsPostsImages cmsPostsImage) {
        // TODO Auto-generated method stub

        return cmsPostsImagesRepository.save(cmsPostsImage);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsPostsImagesService#delete(com.dealmacha.domain.CmsPostsImages)
     */
    @Override
    public void delete(final CmsPostsImages cmsPostsImage) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsPostsImagesService#deleteCmsPostsImagesByPost(java.lang.String)
     */
    @Override
    public void deleteCmsPostsImagesByPost(final String id) {
        // TODO Auto-generated method stub
        List<CmsPostsImages> images = cmsPostsImagesRepository.getImagesByPost(id);

        cmsPostsImagesRepository.delete(images);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ICmsPostsImagesService#edit(com.dealmacha.domain.CmsPostsImages)
     */
    @Override
    public CmsPostsImages edit(final CmsPostsImages cmsPostsImage) {
        // TODO Auto-generated method stub
        return null;
    }

}
