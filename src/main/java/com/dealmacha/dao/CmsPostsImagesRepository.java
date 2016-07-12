/**
 *
 */
package com.dealmacha.dao;

import com.dealmacha.domain.CmsPostsImages;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Jay
 *
 */
public interface CmsPostsImagesRepository extends PagingAndSortingRepository<CmsPostsImages, Serializable> {

    /**
     * @param id
     */
    /* @Modifying
    @Transactional
    @Query("DELETE cpi FROM CmsPostsImages cpi JOIN cpi.cmsPosts cp WHERE cp.id=?1")
    void deletePostImagesByPostId(String id);*/

    /**
     * @param id
     * @return
     */
    @Query("SELECT i FROM CmsPostsImages i JOIN i.cmsPosts cp WHERE cp.id=?1")
    List<CmsPostsImages> getImagesByPost(String id);

}
