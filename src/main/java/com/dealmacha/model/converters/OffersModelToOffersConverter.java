/**
 *
 */
package com.dealmacha.model.converters;

import com.dealmacha.domain.Offers;
import com.dealmacha.model.OffersModel;

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
@Component("offersModelToOffersConverter")
public class OffersModelToOffersConverter implements Converter<OffersModel, Offers> {
    @Autowired
    private ObjectFactory<Offers> offersFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public Offers convert(final OffersModel source) {
        Offers offers = offersFactory.getObject();
        BeanUtils.copyProperties(source, offers);
        /*       offers.setOfferCommissionAmount(source.getOfferCommissionAmount());*/

        return offers;
    }

    @Autowired
    public void setOffersFactory(final ObjectFactory<Offers> offersFactory) {
        this.offersFactory = offersFactory;
    }

}