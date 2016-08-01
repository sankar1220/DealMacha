/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.dao.OffersRepository;
import com.dealmacha.domain.CashbackTransaction;
import com.dealmacha.domain.Offers;
import com.dealmacha.domain.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author arthvedi5
 *
 */
@Component
public class OffersService implements IOffersService {

    private static final Logger LOGGER = Logger.getLogger(OffersService.class);
    @Autowired
    private OffersRepository offersRepository;

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IOffersService#create(com.dealmacha.domain.Offers)
     */
    @Override
    public Offers create(final Offers offers) {
        // TODO Auto-generated method stub
        return offersRepository.save(offers);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IOffersService#deleteOffers(java.lang.String)
     */
    @Override
    public void deleteOffers(final String offersId) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IOffersService#getAll()
     */
    @Override
    public List<Offers> getAll() {
    	List<Offers> offer=(List<Offers>) offersRepository.findAll();		
		List<Offers>  offers=new ArrayList<Offers>();		
		for(Offers cbt:offer){
			Offers ct=new Offers();
			ct.setId(cbt.getId());
			ct.setImageLocation(cbt.getImageLocation());
			
			 
			ct.setImageName(cbt.getImageName());
			ct.setImageType(cbt.getImageType());
			ct.setOfferEndDate(cbt.getOfferEndDate());
	        
			ct.setOfferCommissionAmount(cbt.getOfferCommissionAmount());
			ct.setOfferCommissionAmountType(cbt.getOfferCommissionAmountType());
			ct.setOfferName(cbt.getOfferName());
			ct.setOfferSource(cbt.getOfferSource());
			ct.setOfferStartDate(cbt.getOfferStartDate());
			ct.setOfferCreatedDate(cbt.getOfferCreatedDate());
	        ct.setOfferStatus(cbt.getOfferStatus());
	        ct.setOfferDescription(cbt.getOfferDescription());
	        ct.setOfferType(cbt.getOfferType());
	        ct.setOfferArea(cbt.getOfferArea());
	        ct.setTargetUrl(cbt.getTargetUrl());
			
			offers.add(ct);
			
			
		}
		
		
		return offers;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IOffersService#getOffers(java.lang.String)
     */
    @Override
    public Offers getOffers(final String offersId) {
        // TODO Auto-generated method stub
        return offersRepository.findOne(offersId);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IOffersService#updateOffers(com.dealmacha.domain.Offers)
     */
    @Override
    public Offers updateOffers(final Offers offers) {
        // TODO Auto-generated method stub
        return offersRepository.save(offers);
    }

}
