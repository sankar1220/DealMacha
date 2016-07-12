package com.dealmacha.dao;

import com.dealmacha.domain.Merchant;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MerchantRepository extends PagingAndSortingRepository<Merchant, Serializable> {

    /**
     * @param merchant
     * @return
     */
    @Query("SELECT m FROM Merchant m WHERE UPPER(m.merchantName) = UPPER(?1)")
    Merchant findMerchantByName(String merchant);

}
