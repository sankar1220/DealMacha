/**
 *
 */
package com.dealmacha.model.converters;

import com.dealmacha.domain.MerchantCategoryMargin;
import com.dealmacha.model.MerchantCategoryMarginModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author arthvedi5
 *
 */
@Component("merchantCategoryMarginModelToMerchantCategoryMarginConverter")
public class MerchantCategoryMarginModelToMerchantCategoryMarginConverter implements
        Converter<MerchantCategoryMarginModel, MerchantCategoryMargin> {
    @Autowired
    private ObjectFactory<MerchantCategoryMargin> merchantCategoryMarginFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public MerchantCategoryMargin convert(final MerchantCategoryMarginModel source) {
        MerchantCategoryMargin merchantCategoryMargin = merchantCategoryMarginFactory.getObject();
        BeanUtils.copyProperties(source, merchantCategoryMargin);

        return merchantCategoryMargin;
    }

    @Autowired
    public void setMerchantCategoryMarginFactory(final ObjectFactory<MerchantCategoryMargin> merchantCategoryMarginFactory) {
        this.merchantCategoryMarginFactory = merchantCategoryMarginFactory;
    }

}
