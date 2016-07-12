package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.model.PaymentModel;

import java.util.Collection;

import org.springframework.stereotype.Service;

@Service
public class PaymentBusinessDelegate implements IBusinessDelegate<PaymentModel, PaymentContext, IKeyBuilder<String>, String> {

    @Override
    public PaymentModel create(final PaymentModel model) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final PaymentContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public PaymentModel edit(final IKeyBuilder<String> keyBuilder, final PaymentModel model) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PaymentModel getByKey(final IKeyBuilder<String> keyBuilder, final PaymentContext context) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<PaymentModel> getCollection(final PaymentContext context) {
        // TODO Auto-generated method stub
        return null;
    }

}
