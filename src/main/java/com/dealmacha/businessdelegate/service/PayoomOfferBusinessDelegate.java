package com.dealmacha.businessdelegate.service;

import com.dealmacha.api.Domain.PayoomOffer;
import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.model.PayoomOfferModel;
import com.dealmacha.service.PayoomOfferService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

@Service
public class PayoomOfferBusinessDelegate implements IBusinessDelegate<PayoomOfferModel, PayoomOfferContext, IKeyBuilder<String>, String> {
    Logger LOGGER = Logger.getLogger(PayoomOfferBusinessDelegate.class);
    @Autowired
    private PayoomOfferService payoomOfferService;
    @Autowired
    private ConversionService conversionService;

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#create(java.lang.Object)
     */
    @Override
    public PayoomOfferModel create(final PayoomOfferModel model) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#delete(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final PayoomOfferContext context) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#edit(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      java.lang.Object)
     */
    @Override
    public PayoomOfferModel edit(final IKeyBuilder<String> keyBuilder, final PayoomOfferModel model) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#getByKey(com.dealmacha.businessdelegate.domain.IKeyBuilder,
     *      com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public PayoomOfferModel getByKey(final IKeyBuilder<String> keyBuilder, final PayoomOfferContext context) {

        return null;

    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.businessdelegate.service.IBusinessDelegate#getCollection(com.dealmacha.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public Collection<PayoomOfferModel> getCollection(final PayoomOfferContext context) {

        List<PayoomOffer> payoomOffers = new ArrayList<PayoomOffer>();
        payoomOffers = payoomOfferService.getAll();
        // TODO Auto-generated method stub

        List<PayoomOfferModel> payoomOfferModels = (List<PayoomOfferModel>) conversionService.convert(payoomOffers,
                TypeDescriptor.forObject(payoomOffers),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(PayoomOfferModel.class)));
        return payoomOfferModels;
    }

}
