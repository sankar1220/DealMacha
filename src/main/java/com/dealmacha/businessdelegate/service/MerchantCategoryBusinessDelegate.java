/**
 *
 */
package com.dealmacha.businessdelegate.service;

import com.dealmacha.DBSequences;
import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.Merchant;
import com.dealmacha.domain.MerchantCategory;
import com.dealmacha.domain.MerchantCategoryMargin;
import com.dealmacha.model.MerchantCategoryMarginModel;
import com.dealmacha.model.MerchantCategoryModel;
import com.dealmacha.service.MerchantCategoryService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

/**
 * @author arthvedi5
 *
 */
@Service
public class MerchantCategoryBusinessDelegate
        implements IBusinessDelegate<MerchantCategoryModel, MerchantCategoryContext, IKeyBuilder<String>, String> {

    Logger LOGGER = Logger.getLogger(MerchantCategoryBusinessDelegate.class);
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private MerchantCategoryService merchantCategoryService;

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#create(java.lang.Object)
     */
    @Override
    public MerchantCategoryModel create(MerchantCategoryModel model) {
        MerchantCategory merchantCategory = new MerchantCategory();
        merchantCategory.setCategoryName(model.getCategoryName());
        Merchant merchant = new Merchant();
        merchant.setId(model.getMerchantId());
        merchantCategory.setMerchant(merchant);
        Integer i = merchantCategoryService.getMaxCode();
        if (i == null) {
            i = 0;
            merchantCategory.setMerchantCategoryCount(1);
        }
        else {
            merchantCategory.setMerchantCategoryCount(i + 1);
        }
        Integer c = i + 1;
        String m = DBSequences.MERCHANTCATEGORYCODE.getSequenceName();
        String mc = m.concat(c.toString());
        merchantCategory.setMerchantCategoryCode(mc);
        merchantCategory = merchantCategoryService.create(merchantCategory);
        if (merchantCategory.getId() != null) {
            if (model.getMerchantCategoryMarginModel() != null) {
                List<MerchantCategoryMargin> merchantCategoryMargins = new ArrayList<MerchantCategoryMargin>();
                for (MerchantCategoryMarginModel mcm : model.getMerchantCategoryMarginModel()) {
                    MerchantCategoryMargin merchantCategoryMagin = new MerchantCategoryMargin();
                    merchantCategoryMagin.setId(mcm.getId());
                    merchantCategoryMagin.setMerchantCategory(merchantCategory);
                    merchantCategoryMagin.setChannel(mcm.getChannel());
                    merchantCategoryMagin.setCustomerType(mcm.getCustomerType());
                    String value = mcm.getCommission();
                    BigDecimal commission = new BigDecimal(value.replaceAll(",", " "));
                    merchantCategoryMagin.setCommission(commission);
                    merchantCategoryMagin.setCommissionType(mcm.getCommissionType());
                    String value2 = mcm.getDealmachaCommission();
                    BigDecimal dealmachaCommission = new BigDecimal(value2.replaceAll(",", ""));
                    merchantCategoryMagin.setDealmachaCommission(dealmachaCommission);
                    merchantCategoryMagin.setDealmachaCommissionType(mcm.getDealmachaCommissionType());
                    merchantCategoryMargins.add(merchantCategoryMagin);
                }
                merchantCategory = merchantCategoryService.addMerchantCategoryMargin(merchantCategory, merchantCategoryMargins);
            }
            model.setId(merchantCategory.getId());
        }
        model = conversionService.convert(merchantCategory, MerchantCategoryModel.class);
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#delete(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final MerchantCategoryContext context) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#edit(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      java.lang.Object)
     */
    @Override
    public MerchantCategoryModel edit(final IKeyBuilder<String> keyBuilder, final MerchantCategoryModel model) {
        MerchantCategory merchantCategory = merchantCategoryService.getMerchantCategory(keyBuilder.build().toString());
        merchantCategory.setCategoryName(model.getCategoryName());

        merchantCategory = merchantCategoryService.updateMerchantCategory(merchantCategory);
        model.setId(merchantCategory.getId());
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#getByKey(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public MerchantCategoryModel getByKey(final IKeyBuilder<String> keyBuilder, final MerchantCategoryContext context) {
        MerchantCategory merchantCategory = merchantCategoryService.getMerchantCategory(keyBuilder.build().toString());
        MerchantCategoryModel merchantCategoryModel = conversionService.convert(merchantCategory, MerchantCategoryModel.class);

        return merchantCategoryModel;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#getCollection(com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public Collection<MerchantCategoryModel> getCollection(final MerchantCategoryContext context) {
        List<MerchantCategory> merchantCategory = new ArrayList<MerchantCategory>();
        if (context.getAll() != null) {
            merchantCategory = merchantCategoryService.getAll();
        }
        List<MerchantCategoryModel> merchantCategoryModels = (List<MerchantCategoryModel>) conversionService.convert(merchantCategory,
                TypeDescriptor.forObject(merchantCategory),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(MerchantCategoryModel.class)));
        return merchantCategoryModels;
    }

}
