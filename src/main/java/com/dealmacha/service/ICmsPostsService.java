/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.domain.CmsPosts;

/**
 * @author Jay
 *
 */
public interface ICmsPostsService {

    CmsPosts create(CmsPosts cmsPosts);

    void delete(CmsPosts cmsPosts);

    CmsPosts edit(CmsPosts cmsPosts);

    CmsPosts getCmsPosts(String postsId);

}
