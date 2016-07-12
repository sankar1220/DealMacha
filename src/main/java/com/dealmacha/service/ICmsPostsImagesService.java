/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.domain.CmsPostsImages;

/**
 * @author Jay
 *
 */
public interface ICmsPostsImagesService {
    CmsPostsImages create(CmsPostsImages cmsPostsImage);

    void delete(CmsPostsImages cmsPostsImage);

    /**
     * @param id
     */
    void deleteCmsPostsImagesByPost(String id);

    CmsPostsImages edit(CmsPostsImages cmsPostsImage);
}
