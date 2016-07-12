/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.domain.MerchantCategoryMargin;

import java.util.List;

/**
 * @author arthvedi5
 *
 */
public interface IMerchantCategoryMarginService {
    MerchantCategoryMargin create(MerchantCategoryMargin merchantCategoryMargin);

    void deleteMerchantCategoryMargin(String merchantCategoryMarginId);

    List<MerchantCategoryMargin> getAll();

    /**
     * @param string
     * @return
     */
    MerchantCategoryMargin getMerchantCategoryMargin(String merchantCategoryMarginId);

    MerchantCategoryMargin updateMerchantCategoryMargin(MerchantCategoryMargin merchantCategoryMargin);
}
