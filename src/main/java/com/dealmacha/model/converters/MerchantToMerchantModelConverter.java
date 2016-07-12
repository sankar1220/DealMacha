package com.dealmacha.model.converters;

import com.dealmacha.domain.Merchant;
import com.dealmacha.model.ClickTransactionModel;
import com.dealmacha.model.MerchantCategoryModel;
import com.dealmacha.model.MerchantModel;
import com.dealmacha.model.TransactionModel;
import com.dealmacha.util.CollectionTypeDescriptor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Naveen
 *
 */
@Component("merchantToMerchantModelConverter")
public class MerchantToMerchantModelConverter implements Converter<Merchant, MerchantModel> {

    private static final Logger LOGGER = Logger.getLogger(MerchantToMerchantModelConverter.class);
    @Autowired
    private ObjectFactory<MerchantModel> merchantModelFactory;
    @Autowired
    private ConversionService conversionService;

    /**
     * {@inheritDoc}
     *
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public MerchantModel convert(final Merchant source) {
        // TODO Auto-generated method stub
        MerchantModel merchantModel = merchantModelFactory.getObject();

        BeanUtils.copyProperties(source, merchantModel);
        if (source.getId() != null) {
             merchantModel.setAffiliateId(source.getAffiliateId());
         }
        merchantModel.setUrl(source.getUrl());
        if (CollectionUtils.isNotEmpty(source.getClickTransactions())) {
            List<ClickTransactionModel> converted = (List<ClickTransactionModel>) conversionService.convert(source.getClickTransactions(),
                    TypeDescriptor.forObject(source.getClickTransactions()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), ClickTransactionModel.class));
            merchantModel.getClickTransactionModels().addAll(converted);
        }
        if (CollectionUtils.isNotEmpty(source.getTransaction())) {
            List<TransactionModel> converted = (List<TransactionModel>) conversionService.convert(source.getTransaction(),
                    TypeDescriptor.forObject(source.getTransaction()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), TransactionModel.class));
            merchantModel.getTransactionModel().addAll(converted);
        }
        if (CollectionUtils.isNotEmpty(source.getMerchantCategory())) {
            List<MerchantCategoryModel> converted = (List<MerchantCategoryModel>) conversionService.convert(source.getMerchantCategory(),
                    TypeDescriptor.forObject(source.getMerchantCategory()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), MerchantCategoryModel.class));
            merchantModel.getMerchantCategoryModel().addAll(converted);
        }

        return merchantModel;

    }

    @Autowired
    public void setMerchantFactory(final ObjectFactory<MerchantModel> merchantModelFactory) {
        this.merchantModelFactory = merchantModelFactory;
    }
}
