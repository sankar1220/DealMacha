/**
 *
 */
package com.dealmacha.model.converters;

import com.dealmacha.domain.MerchantCategory;
import com.dealmacha.domain.MerchantCategoryMargin;
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
@Component("merchantCategoryModelToMerchantCategoryConverter")
public class MerchantCategoryModelToMerchantCategoryConverter implements Converter<MerchantCategoryModel, MerchantCategory> {
    @Autowired
    private ObjectFactory<MerchantCategory> merchantCategoryFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public MerchantCategory convert(final MerchantCategoryModel source) {
        MerchantCategory merchantCategory = merchantCategoryFactory.getObject();
        BeanUtils.copyProperties(source, merchantCategory);

        if (CollectionUtils.isNotEmpty(source.getMerchantCategoryMarginModel())) {
            List<MerchantCategoryMargin> converted = (List<MerchantCategoryMargin>) conversionService.convert(
                    source.getMerchantCategoryMarginModel(), TypeDescriptor.forObject(source.getMerchantCategoryMarginModel()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), MerchantCategoryMargin.class));
            merchantCategory.getMerchantCategoryMargin().addAll(converted);
        }
        return merchantCategory;
    }

    @Autowired
    public void setMerchantCategoryFactory(final ObjectFactory<MerchantCategory> merchantCategoryFactory) {
        this.merchantCategoryFactory = merchantCategoryFactory;
    }

}