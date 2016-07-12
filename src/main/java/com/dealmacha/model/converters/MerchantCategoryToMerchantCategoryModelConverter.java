/**
 *
 */
package com.dealmacha.model.converters;

import com.dealmacha.domain.MerchantCategory;
import com.dealmacha.model.MerchantCategoryMarginModel;
import com.dealmacha.model.MerchantCategoryModel;
import com.dealmacha.util.CollectionTypeDescriptor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author arthvedi5
 *
 */
@Component("merchantCategoryToMerchantCategoryModelConverter")
public class MerchantCategoryToMerchantCategoryModelConverter implements Converter<MerchantCategory, MerchantCategoryModel> {
    @Autowired
    private ObjectFactory<MerchantCategoryModel> merchantCategoryModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public MerchantCategoryModel convert(final MerchantCategory source) {
        MerchantCategoryModel merchantCategoryModel = merchantCategoryModelFactory.getObject();
        BeanUtils.copyProperties(source, merchantCategoryModel);
        if (source.getId() != null) {
            merchantCategoryModel.setMerchantId(source.getMerchant().getId());
        }

        merchantCategoryModel.setMerchantCategoryCount(Integer.toString(source.getMerchantCategoryCount()));

        if (CollectionUtils.isNotEmpty(source.getMerchantCategoryMargin())) {
            List<MerchantCategoryMarginModel> converted = (List<MerchantCategoryMarginModel>) conversionService.convert(
                    source.getMerchantCategoryMargin(), TypeDescriptor.forObject(source.getMerchantCategoryMargin()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), MerchantCategoryMarginModel.class));
            merchantCategoryModel.getMerchantCategoryMarginModel().addAll(converted);
        }
        return merchantCategoryModel;
    }

    @Autowired
    public void setMerchantCategoryModelFactory(final ObjectFactory<MerchantCategoryModel> merchantCategoryModelFactory) {
        this.merchantCategoryModelFactory = merchantCategoryModelFactory;
    }

}