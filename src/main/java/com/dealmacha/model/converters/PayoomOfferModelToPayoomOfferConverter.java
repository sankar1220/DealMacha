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
@Component("payoomOfferModelToPayoomOfferConverter")
public class PayoomOfferModelToPayoomOfferConverter implements Converter<PayoomOfferModel, PayoomOffer> {
    @Autowired
    private ObjectFactory<PayoomOffer> payoomOfferFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public PayoomOffer convert(final PayoomOfferModel source) {
        PayoomOffer payoomOffer = payoomOfferFactory.getObject();
        BeanUtils.copyProperties(source, payoomOffer);

        return payoomOffer;
    }

    @Autowired
    public void setPayoomOfferFactory(final ObjectFactory<PayoomOffer> payoomOfferFactory) {
        this.payoomOfferFactory = payoomOfferFactory;
    }

}