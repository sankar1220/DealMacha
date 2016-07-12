/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.dao.MerchantCategoryMarginRepository;
import com.dealmacha.dao.MerchantCategoryRepository;
import com.dealmacha.domain.MerchantCategory;
import com.dealmacha.domain.MerchantCategoryMargin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author arthvedi5
 *
 */
@Component
public class MerchantCategoryService implements IMerchantCategoryService {
    @Autowired
    private MerchantCategoryRepository merchantCategoryRepository;
    @Autowired
    private MerchantCategoryMarginService merchantCategoryMarginService;
    @Autowired
    private MerchantCategoryMarginRepository merchantCategoryMarginRepository;

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantCategoryService#addMerchantCategory(com.dealmacha.domain.MerchantCategory, java.util.Set)
     */
    @Override
    public MerchantCategory addMerchantCategoryMargin(final MerchantCategory merchantCategory,
            final List<MerchantCategoryMargin> merchantCategoryMargins) {

        Validate.notNull(merchantCategory, "merchantCategory must not be null");
        Set<MerchantCategoryMargin> merchantCatMargins = new HashSet<MerchantCategoryMargin>(merchantCategoryMargins);
        for (MerchantCategoryMargin merchantCatMargin : merchantCategoryMargins) {
            MerchantCategoryMargin merCategoryMargin = merchantCatMargin;
            merCategoryMargin = merchantCategoryMarginService.create(merCategoryMargin);
            merchantCatMargins.add(merCategoryMargin);
        }
        merchantCategory.setMerchantCategoryMargin(merchantCatMargins);
        return merchantCategory;

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantCategoryService#create(com.dealmacha.domain.MerchantCategory)
     */
    @Override
    public MerchantCategory create(MerchantCategory merchantCategory) {

        merchantCategory = merchantCategoryRepository.save(merchantCategory);
        return merchantCategory;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantCategoryService#deleteMerchantCategory(java.lang.String)
     */
    @Override
    public void deleteMerchantCategory(final String merchantCategoryId) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantCategoryService#getAll()
     */
    @Override
    public List<MerchantCategory> getAll() {
        // TODO Auto-generated method stub
        return (List<MerchantCategory>) merchantCategoryRepository.findAll();
    }

    /**
     * @return
     */

    public Integer getMaxCode() {
        // TODO Auto-generated method stub
        return merchantCategoryRepository.getMaxCode();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantCategoryService#getMerchantCategory(java.lang.String)
     */
    @Override
    public MerchantCategory getMerchantCategory(final String merchantCategoryId) {
        // TODO Auto-generated method stub
        return merchantCategoryRepository.findOne(merchantCategoryId);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantCategoryService#updateMerchantCategory(com.dealmacha.domain.MerchantCategory)
     */
    @Override
    public MerchantCategory updateMerchantCategory(final MerchantCategory merchantCategory) {
        // TODO Auto-generated method stub
        return merchantCategoryRepository.save(merchantCategory);
    }

}
