package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.ClickTransaction;
import com.dealmacha.domain.Merchant;
import com.dealmacha.domain.Users;
import com.dealmacha.model.ClickTransactionModel;
import com.dealmacha.service.ClickTransactionService;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class ClickTransactionBusinessDelegate implements
        IBusinessDelegate<ClickTransactionModel, ClickTransactionContext, IKeyBuilder<String>, String> {

    Logger LOGGER = Logger.getLogger(ClickTransactionBusinessDelegate.class);
    @Autowired
    private ClickTransactionService clickTransactionService;
    @Autowired
    private ConversionService conversionService;

    @Override
    public ClickTransactionModel create(ClickTransactionModel model) {
        ClickTransaction clickTransaction = new ClickTransaction();
        clickTransaction.setId(model.getId());
        clickTransaction.setClickedSource(model.getClickedSource());
        clickTransaction.setClickedTarget(model.getClickedTarget());
        clickTransaction.setClickedTargetId(model.getClickedTargetId());
        clickTransaction.setCreatedDate(new Date());
        Merchant merchant = new Merchant();
        merchant.setId(model.getId());
        clickTransaction.setMerchant(merchant);
        clickTransaction.setOrderStatus(model.getOrderStatus());
        Users users = new Users();
        users.setId(model.getUsersId());
        clickTransaction.setUsers(users);
        clickTransaction.setStatus(model.getStatus());
        clickTransaction.setUrlClicked(model.getUrlClicked());
        clickTransaction = clickTransactionService.create(clickTransaction);
        model = conversionService.convert(clickTransaction, ClickTransactionModel.class);
        return model;
    }

    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final ClickTransactionContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public ClickTransactionModel edit(final IKeyBuilder<String> keyBuilder, final ClickTransactionModel model) {
        ClickTransaction clickTransaction = clickTransactionService.getClickTransaction(keyBuilder.build().toString());
        clickTransaction.setClickedSource(model.getClickedSource());
        clickTransaction.setClickedTarget(model.getClickedTarget());
        clickTransaction.setOrderStatus(model.getOrderStatus());
        clickTransaction.setStatus(model.getStatus());
        clickTransaction.setUrlClicked(model.getUrlClicked());
        clickTransaction = clickTransactionService.updateClickTransaction(clickTransaction);
        model.setId(clickTransaction.getId());
        return model;
    }

    @Override
    public ClickTransactionModel getByKey(final IKeyBuilder<String> keyBuilder, final ClickTransactionContext context) {
        ClickTransaction clickTransaction = clickTransactionService.getClickTransaction(keyBuilder.build().toString());
        ClickTransactionModel clickTransactionModel = conversionService.convert(clickTransaction, ClickTransactionModel.class);
        return clickTransactionModel;
    }

    @Override
    public Collection<ClickTransactionModel> getCollection(final ClickTransactionContext context) {
        // TODO Auto-generated method stub
        return null;
    }

}
