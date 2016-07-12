package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.Merchant;
import com.dealmacha.model.MerchantModel;
import com.dealmacha.service.MerchantService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

@Service
public class MerchantBusinessDelegate implements IBusinessDelegate<MerchantModel, MerchantContext, IKeyBuilder<String>, String> {

    Logger LOGGER = Logger.getLogger(MerchantBusinessDelegate.class);
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private MerchantService merchantService;

    @Override
    public MerchantModel create(MerchantModel model) {
        Merchant merchant = new Merchant();
        merchant.setId(model.getId());
        merchant.setAffiliateId(model.getAffiliateId());
        merchant.setAffiliateKey(model.getAffiliateKey());
        merchant.setMerchantImage(model.getMerchantImage());
        merchant.setMerchantName(model.getMerchantName());
        merchant.setDescription(model.getDescription());
        merchant.setMerchantToken(model.getMerchantToken());
        merchant.setUrl(model.getUrl());
        merchant = merchantService.create(merchant);
        model = conversionService.convert(merchant, MerchantModel.class);
        return model;
    }

    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final MerchantContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public MerchantModel edit(final IKeyBuilder<String> keyBuilder, final MerchantModel model) {
        Merchant merchant = merchantService.getMerchant(keyBuilder.build().toString());
        merchant.setMerchantImage(model.getMerchantImage());
        merchant.setMerchantName(model.getMerchantName());
        merchant.setMerchantToken(model.getMerchantToken());
        merchant = merchantService.updateMerchant(merchant);
        model.setId(merchant.getId());
        return model;
    }

    @Override
    public MerchantModel getByKey(final IKeyBuilder<String> keyBuilder, final MerchantContext context) {
        Merchant merchant = merchantService.getMerchant(keyBuilder.build().toString());
        MerchantModel merchantModel = conversionService.convert(merchant, MerchantModel.class);

        return merchantModel;
    }

    @Override
    public Collection<MerchantModel> getCollection(final MerchantContext context) {
        List<Merchant> merchant = new ArrayList<Merchant>();
        if (context.getAll() != null) {
            merchant = merchantService.getAll();
        }
        List<MerchantModel> merchantModels = (List<MerchantModel>) conversionService.convert(merchant, TypeDescriptor.forObject(merchant),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(MerchantModel.class)));
        return merchantModels;

    }

}
