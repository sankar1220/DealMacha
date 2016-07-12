/**
 *
 */
package com.dealmacha.dao;

import com.dealmacha.domain.MerchantCategory;
import com.dealmacha.domain.Users;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author arthvedi5
 *
 */
public interface MerchantCategoryRepository extends PagingAndSortingRepository<MerchantCategory, Serializable> {

    /**
     * @param a
     * @return
     */
    @Query("select mc from MerchantCategory mc where mc.merchantCategoryCode=?1")
    Users findByMerchantCategoryCode(String a);

    /**
     * @return
     */
    @Query("select MAX(merchantCategoryCount) from MerchantCategory mc")
    Integer getMaxCode();
}
