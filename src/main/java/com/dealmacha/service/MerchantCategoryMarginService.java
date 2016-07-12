/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.dao.MerchantCategoryMarginRepository;
import com.dealmacha.domain.MerchantCategoryMargin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author arthvedi5
 *
 */
@Component
public class MerchantCategoryMarginService implements IMerchantCategoryMarginService {
    @Autowired
    private MerchantCategoryMarginRepository merchantCategoryMarginRepository;

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantCategoryMarginService#create(com.dealmacha.domain.MerchantCategoryMargin)
     */
    @Override
    public MerchantCategoryMargin create(final MerchantCategoryMargin merchantCategoryMargin) {

        return merchantCategoryMarginRepository.save(merchantCategoryMargin);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantCategoryMarginService#deleteMerchantCategoryMargin(java.lang.String)
     */
    @Override
    public void deleteMerchantCategoryMargin(final String merchantCategoryMarginId) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantCategoryMarginService#getAll()
     */
    @Override
    public List<MerchantCategoryMargin> getAll() {
        // TODO Auto-generated method stub
        return (List<MerchantCategoryMargin>) merchantCategoryMarginRepository.findAll();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantCategoryMarginService#getMerchantCategoryMargin(java.lang.String)
     */
    @Override
    public MerchantCategoryMargin getMerchantCategoryMargin(final String merchantCategoryMarginId) {
        // TODO Auto-generated method stub
        return merchantCategoryMarginRepository.findOne(merchantCategoryMarginId);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantCategoryMarginService#updateMerchantCategoryMargin(com.dealmacha.domain.MerchantCategoryMargin)
     */
    @Override
    public MerchantCategoryMargin updateMerchantCategoryMargin(final MerchantCategoryMargin merchantCategoryMargin) {
        // TODO Auto-generated method stub
        return merchantCategoryMarginRepository.save(merchantCategoryMargin);
    }

}
