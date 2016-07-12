/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.api.Domain.PayoomOffer;
import com.dealmacha.api.resources.PayoomApiResourceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mohan
 *
 */

@Component
public class PayoomOfferService implements IPayoomOfferService {
    @Autowired
    private PayoomApiResourceService payoomApiResourceService;

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IPayoomOfferService#create(com.dealmacha.api.Domain.PayoomOffer)
     */
    @Override
    public PayoomOffer create(final PayoomOffer payoomOffer) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IPayoomOfferService#deletePayoomOffer(java.lang.String)
     */
    @Override
    public void deletePayoomOffer(final String payoomOfferId) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IPayoomOfferService#getAll()
     */
    @Override
    public List<PayoomOffer> getAll() {

        // TODO Auto-generated method stub
        return payoomApiResourceService.getPayoomOffers("", "");
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IPayoomOfferService#getPayoomOffer(java.lang.String)
     */
    @Override
    public PayoomOffer getPayoomOffer(final String payoomOfferId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IPayoomOfferService#updatePayoomOffer(com.dealmacha.api.Domain.PayoomOffer)
     */
    @Override
    public PayoomOffer updatePayoomOffer(final PayoomOffer payoomOffer) {
        // TODO Auto-generated method stub
        return null;
    }

}
