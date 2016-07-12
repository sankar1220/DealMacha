/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.domain.MerchantCategory;
import com.dealmacha.domain.MerchantCategoryMargin;

import java.util.List;

/**
 * @author arthvedi5
 *
 */
public interface IMerchantCategoryService {
    MerchantCategory addMerchantCategoryMargin(MerchantCategory merchantCategory, List<MerchantCategoryMargin> merchantCategorymargin);

    MerchantCategory create(MerchantCategory merchantCategory);

    void deleteMerchantCategory(String merchantCategoryId);

    List<MerchantCategory> getAll();

    MerchantCategory getMerchantCategory(String merchantCategoryId);

    MerchantCategory updateMerchantCategory(MerchantCategory merchantCategory);
}
