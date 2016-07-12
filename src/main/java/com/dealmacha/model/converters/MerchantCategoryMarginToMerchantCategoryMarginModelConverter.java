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
@Component("merchantCategoryMarginToMerchantCategoryMarginModelConverter")
public class MerchantCategoryMarginToMerchantCategoryMarginModelConverter
        implements Converter<MerchantCategoryMargin, MerchantCategoryMarginModel> {
    @Autowired
    private ObjectFactory<MerchantCategoryMarginModel> merchantCategoryMarginModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public MerchantCategoryMarginModel convert(final MerchantCategoryMargin source) {
        MerchantCategoryMarginModel merchantCategoryMarginModel = merchantCategoryMarginModelFactory.getObject();
        BeanUtils.copyProperties(source, merchantCategoryMarginModel);
        if (source.getId() != null) {
            merchantCategoryMarginModel.setId(source.getId());
        }
        if (source.getMerchantCategory() != null) {
            merchantCategoryMarginModel.setMerchantCategoryId(source.getMerchantCategory().getId());
        }
        /*       BigDecimal bd = new BigDecimal(source.getCommission().toString());*/
        merchantCategoryMarginModel.setCommission(source.getCommission().toString());
        merchantCategoryMarginModel.setDealmachaCommission(source.getDealmachaCommission().toString());
        /*-if (source.getId() != null) {
                merchantCategoryMarginModel.setMerchantCategoryId(source.getMerchantCategory());
               merchantCategoryMarginModel.setMargin(source.getMargin());
               merchantCategoryMarginModel.setChannel(source.getChannel());
               merchantCategoryMarginModel.setCustomerType(source.getCustomerType());
           }*/

        /* if (CollectionUtils.isNotEmpty(source.getUsers())) {
             List<UsersModel> converted = (List<UsersModel>) conversionService.convert(source.getUsers(),
                     TypeDescriptor.forObject(source.getUsers()),
                     CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), UsersModel.class));
             merchantCategoryMarginModel.getUsersModels().addAll(converted);
         }*/

        return merchantCategoryMarginModel;
    }

    @Autowired
    public void setMerchantCategoryMarginModelFactory(final ObjectFactory<MerchantCategoryMarginModel> merchantCategoryMarginModelFactory) {
        this.merchantCategoryMarginModelFactory = merchantCategoryMarginModelFactory;
    }

}