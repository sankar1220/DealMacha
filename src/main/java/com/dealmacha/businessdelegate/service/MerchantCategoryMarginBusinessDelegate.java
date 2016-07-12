/**
 *
 */
package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.MerchantCategory;
import com.dealmacha.domain.MerchantCategoryMargin;
import com.dealmacha.model.MerchantCategoryMarginModel;
import com.dealmacha.service.MerchantCategoryMarginService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

/**
 * @author arthvedi5
 *
 */
@Service
public class MerchantCategoryMarginBusinessDelegate implements
IBusinessDelegate<MerchantCategoryMarginModel, MerchantCategoryMarginContext, IKeyBuilder<String>, String> {
    @Autowired
    private MerchantCategoryMarginService merchantCategoryMarginService;
    @Autowired
    private ConversionService conversionService;

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#create(java.lang.Object)
     */
    @Override
    public MerchantCategoryMarginModel create(MerchantCategoryMarginModel model) {
        MerchantCategoryMargin merchantCategoryMargin = new MerchantCategoryMargin();
        merchantCategoryMargin.setId(model.getId());
        merchantCategoryMargin.setChannel(model.getChannel());
        merchantCategoryMargin.setCustomerType(model.getCustomerType());
        String value = model.getCommission();
        BigDecimal commission = new BigDecimal(value.replaceAll(",", " "));
        merchantCategoryMargin.setCommission(commission);
        merchantCategoryMargin.setCommissionType(model.getCommissionType());
        String value2 = model.getDealmachaCommission();
        BigDecimal dealmachaCommission = new BigDecimal(value2.replaceAll(",", ""));
        merchantCategoryMargin.setDealmachaCommission(dealmachaCommission);
        merchantCategoryMargin.setDealmachaCommissionType(model.getDealmachaCommissionType());
        MerchantCategory merchantCategory = new MerchantCategory();
        merchantCategory.setId(model.getMerchantCategoryId());
        merchantCategoryMargin.setMerchantCategory(merchantCategory);
        merchantCategoryMargin = merchantCategoryMarginService.create(merchantCategoryMargin);
        model = conversionService.convert(merchantCategoryMargin, MerchantCategoryMarginModel.class);
        model.setId(merchantCategoryMargin.getId());
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#delete(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final MerchantCategoryMarginContext context) {

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#edit(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      java.lang.Object)
     */
    @Override
    public MerchantCategoryMarginModel edit(final IKeyBuilder<String> keyBuilder, final MerchantCategoryMarginModel model) {
        MerchantCategoryMargin merchantCategoryMargin = merchantCategoryMarginService.getMerchantCategoryMargin(keyBuilder.build()
                .toString());
        merchantCategoryMargin.setCustomerType(model.getCustomerType());
        merchantCategoryMargin.setChannel(model.getChannel());
        String value = model.getCommission();
        BigDecimal commission = new BigDecimal(value.replaceAll(",", " "));
        merchantCategoryMargin.setCommission(commission);
        merchantCategoryMargin.setCommissionType(model.getCommissionType());
        String value2 = model.getDealmachaCommission();
        BigDecimal dealmachaCommission = new BigDecimal(value2.replaceAll(",", ""));
        merchantCategoryMargin.setDealmachaCommission(dealmachaCommission);
        merchantCategoryMargin.setDealmachaCommissionType(model.getDealmachaCommissionType());
        merchantCategoryMargin = merchantCategoryMarginService.updateMerchantCategoryMargin(merchantCategoryMargin);
        model.setId(merchantCategoryMargin.getId());
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#getByKey(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public MerchantCategoryMarginModel getByKey(final IKeyBuilder<String> keyBuilder, final MerchantCategoryMarginContext context) {
        MerchantCategoryMargin merchantCategoryMargin = merchantCategoryMarginService.getMerchantCategoryMargin(keyBuilder.build()
                .toString());
        MerchantCategoryMarginModel merchantCategoryMarginModel = conversionService.convert(merchantCategoryMargin,
                MerchantCategoryMarginModel.class);

        return merchantCategoryMarginModel;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#getCollection(com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public Collection<MerchantCategoryMarginModel> getCollection(final MerchantCategoryMarginContext context) {
        List<MerchantCategoryMargin> merchantCategoryMargin = new ArrayList<MerchantCategoryMargin>();

        if (context.getAll() != null) {
            merchantCategoryMargin = merchantCategoryMarginService.getAll();
        }
        List<MerchantCategoryMarginModel> merchantCategoryMarginModels = (List<MerchantCategoryMarginModel>) conversionService.convert(
                merchantCategoryMargin, TypeDescriptor.forObject(merchantCategoryMargin),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(MerchantCategoryMarginModel.class)));
        return merchantCategoryMarginModels;
    }

}
