/**
 *
 */
package com.dealmacha.model.converters;

import com.dealmacha.api.Domain.PayoomOffer;
import com.dealmacha.model.PayoomOfferModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author mohan
 *
 */
@Component("payoomOfferToPayoomOfferModelConverter")
public class PayoomOfferToPayoomOfferModelConverter implements Converter<PayoomOffer, PayoomOfferModel> {
    @Autowired
    private ObjectFactory<PayoomOfferModel> payoomOfferModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public PayoomOfferModel convert(final PayoomOffer source) {
        PayoomOfferModel payoomOfferModel = payoomOfferModelFactory.getObject();
        BeanUtils.copyProperties(source, payoomOfferModel);

        return payoomOfferModel;
    }

    @Autowired
    public void setPayoomOfferModelFactory(final ObjectFactory<PayoomOfferModel> payoomOfferModelFactory) {
        this.payoomOfferModelFactory = payoomOfferModelFactory;
    }

}