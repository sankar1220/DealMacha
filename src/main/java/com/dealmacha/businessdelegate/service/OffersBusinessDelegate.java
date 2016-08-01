/**
 *
 */
package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.Offers;
import com.dealmacha.model.OffersModel;
import com.dealmacha.service.OffersService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
public class OffersBusinessDelegate implements IBusinessDelegate<OffersModel, OffersContext, IKeyBuilder<String>, String> {
    @Autowired
    private OffersService offersService;
    @Autowired
    private ConversionService conversionService;

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#create(com.dealmacha.businessdelegate.domain.IModel)
     */
    @Override
    public OffersModel create(OffersModel model) {
        Offers offers = new Offers();
         offers.setImageLocation(model.getImageLocation());
         offers.setImageName(model.getImageName());
         offers.setImageType(model.getImageType());
        offers.setOfferCommissionAmount(Double.parseDouble(model.getOfferCommissionAmount()));
        offers.setOfferCommissionAmountType(model.getOfferCommissionAmountType());
        offers.setOfferName(model.getOfferName());
        offers.setOfferSource(model.getOfferSource());
    	SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(model.getOfferStartDate()!=null){
        	String offStartDate = model.getOfferStartDate();
        	Date offstDate;
			try {
				offstDate = dformat.parse(offStartDate);
				offers.setOfferStartDate(offstDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        if(model.getOfferEndDate()!=null){
        	String offereDate = model.getOfferEndDate();
        	Date offerendDate;
			try {
				offerendDate = dformat.parse(offereDate);
				offers.setOfferEndDate(offerendDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        offers.setOfferCreatedDate(new Date());
        offers.setOfferStatus(model.getOfferStatus());
        offers.setOfferDescription(model.getOfferDescription());
        offers.setOfferType(model.getOfferType());
        offers.setOfferArea(model.getOfferArea());
        offers.setTargetUrl(model.getTargetUrl());
        offers.setId(model.getId());
        offers = offersService.create(offers);
        model = conversionService.convert(offers, OffersModel.class);
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#delete(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final OffersContext context) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#edit(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.domain.IModel)
     */
    @Override
    public OffersModel edit(final IKeyBuilder<String> keyBuilder, final OffersModel model) {
        Offers offers = offersService.getOffers(keyBuilder.build().toString());
        offers.setImageLocation(model.getImageLocation());
        offers.setImageName(model.getImageName());
        offers.setImageType(model.getImageType());
        /*  String value = model.getOfferCommissionAmount();
          BigDecimal offerMarginAmount = new BigDecimal(value.replaceAll(",", " "));*/
        offers.setOfferCommissionAmount(Double.parseDouble(model.getOfferCommissionAmount()));
        offers.setOfferCommissionAmountType(model.getOfferCommissionAmountType());
        offers.setOfferName(model.getOfferName());
        offers.setOfferSource(model.getOfferSource());
        offers.setOfferStatus(model.getOfferStatus());
        offers.setTargetUrl(model.getTargetUrl());
        offers = offersService.updateOffers(offers);
        model.setId(offers.getId());
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#getByKey(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public OffersModel getByKey(final IKeyBuilder<String> keyBuilder, final OffersContext context) {
        Offers offers = offersService.getOffers(keyBuilder.build().toString());
        OffersModel offersModel = conversionService.convert(offers, OffersModel.class);
        return offersModel;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#getCollection(com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public Collection<OffersModel> getCollection(final OffersContext context) {
        List<Offers> offers = new ArrayList<Offers>();
        if (context.getAll() != null) {
            offers = offersService.getAll();
        }
        /* if (context.getOffersFromPayoomOffer() != null) {
             offers = offersService.getOffersFromPayoomOffer();
         }*/
        List<OffersModel> offersModels = (List<OffersModel>) conversionService.convert(offers, TypeDescriptor.forObject(offers),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(OffersModel.class)));
        return offersModels;
    }

}
