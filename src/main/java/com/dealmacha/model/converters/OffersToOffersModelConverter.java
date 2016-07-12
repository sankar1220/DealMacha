/**
 *
 */
package com.dealmacha.model.converters;

import com.dealmacha.domain.Offers;
import com.dealmacha.model.OffersModel;

import java.text.SimpleDateFormat;

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
@Component("offersToOffersModelConverter")
public class OffersToOffersModelConverter implements Converter<Offers, OffersModel> {
    @Autowired
    private ObjectFactory<OffersModel> offersModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public OffersModel convert(final Offers source) {
        OffersModel offersModel = offersModelFactory.getObject();
        BeanUtils.copyProperties(source, offersModel);
        offersModel.setOfferCommissionAmount(source.getOfferCommissionAmount().toString());
        String ds1 = null;
        if (source.getOfferCreatedDate() != null) {
            ds1 = source.getOfferCreatedDate().toString();
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        if (ds1 != null) {
            try {
                String ds2 = sdf2.format(source.getOfferCreatedDate());
                offersModel.setOfferCreatedDate(ds2);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        String ds2 = null;
        if (source.getOfferStartDate() != null) {
            ds2 = source.getOfferStartDate().toString();
        }
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf4 = new SimpleDateFormat("dd/MM/yyyy");
        if (ds2 != null) {
            try {
                String ds3 = sdf4.format(source.getOfferStartDate());
                offersModel.setOfferStartDate(ds3);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        String ds3 = null;
        if (source.getOfferEndDate() != null) {
            ds3 = source.getOfferEndDate().toString();
        }
        SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf6 = new SimpleDateFormat("dd/MM/yyyy");
        if (ds3 != null) {
            try {
                String ds4 = sdf6.format(source.getOfferEndDate());
                offersModel.setOfferEndDate(ds4);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return offersModel;
    }

    @Autowired
    public void setOffersModelFactory(final ObjectFactory<OffersModel> offersModelFactory) {
        this.offersModelFactory = offersModelFactory;
    }

}
