/**
 *
 */
package com.dealmacha.dao;

import com.dealmacha.domain.CmsPosts;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Jay
 *
 */
public interface CmsPostsRepository extends PagingAndSortingRepository<CmsPosts, Serializable> {

}
