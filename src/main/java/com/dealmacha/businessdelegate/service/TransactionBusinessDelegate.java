package com.dealmacha.businessdelegate.service;

import com.dealmacha.DBSequences;
import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.Merchant;
import com.dealmacha.domain.Transaction;
import com.dealmacha.domain.Users;
import com.dealmacha.model.TransactionModel;
import com.dealmacha.service.MerchantService;
import com.dealmacha.service.TransactionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

@Service
public class TransactionBusinessDelegate implements IBusinessDelegate<TransactionModel, TransactionContext, IKeyBuilder<String>, String> {
    Logger LOGGER = Logger.getLogger(TransactionBusinessDelegate.class);
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private ConversionService conversionService;

    @Override
    public TransactionModel create(TransactionModel model) {
        Transaction transaction = new Transaction();
        Integer i = transactionService.getMaxCode();
        if (i == null || i==0) {
            i = 0;
            transaction.setTransactionCount(i+1);
        }
        else {
            transaction.setTransactionCount(i + 1);
        }
        Integer co = i + 1;
        String m = DBSequences.USERS.getSequenceName();
        String mc = m.concat(co.toString());
        transaction.setCode(mc);
        transaction.setCreatedDate(new Date());
        transaction.setCashbackAmount(Double.parseDouble(model.getCashbackAmount()));
        transaction.setOrderStatus(model.getOrderStatus());
        transaction.setPaymentType(model.getPaymentType());
        transaction.setRefundStatus(model.getRefundStatus());
        transaction.setSource(model.getSource());
        transaction.setTargetUrl(model.getTargetUrl());
        transaction.setTransactionFor(model.getTransactionFor());
        transaction.setTransactionMerchantErrorCode(model.getTransactionMerchantErrorCode());
        transaction.setTransactionMerchantMsg(model.getTransactionMerchantMsg());
        Users users = new Users();
        users.setId(model.getUsersId());
        transaction.setUsers(users);
        Merchant merchant = new Merchant();
        merchant.setId(model.getMerchantId());
        transaction.setMerchant(merchant);
        transaction.setAmount(Double.parseDouble(model.getAmount()));
        transaction.setPaymentType(model.getPaymentType());
        transaction.setTransactionErrorCode(model.getTransactionErrorCode());
        transaction = transactionService.create(transaction);
        model = conversionService.convert(transaction, TransactionModel.class);
        return model;
    }

    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final TransactionContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public TransactionModel edit(final IKeyBuilder<String> keyBuilder, final TransactionModel model) {
        Transaction transaction = transactionService.getTransaction(keyBuilder.build().toString());
        transaction.setTransactionMerchantMsg(model.getTransactionMerchantMsg());
        
        return null;
    }

    @Override
    public TransactionModel getByKey(final IKeyBuilder<String> keyBuilder, final TransactionContext context) {
        Transaction transaction = transactionService.getTransaction(keyBuilder.build().toString());
        TransactionModel transactionModel = conversionService.convert(transaction, TransactionModel.class);

        return transactionModel;
    }

    @Override
    public Collection<TransactionModel> getCollection(final TransactionContext context) {
        List<Transaction> transaction = new ArrayList<Transaction>();
        if (context.getUsersId() != null) {
            transaction = transactionService.getUsersTransaction(context.getUsersId());
        }
        if (context.getAll() != null) {
            transaction = transactionService.getAll();
        }
        if (context.getMerchantId() != null) {
            transaction = transactionService.getMerchantTransaction(context.getMerchantId());
        }
        if (context.getDateId() != null) {
            transaction = transactionService.getDateTransaction(context.getDateId());
        }
        if(context.getFailTransactions()!=null){
        	transaction=transactionService.getFailTransactions();
        }
/*if(context.getAllTotalTransaction()!=null){
	transaction=transactionService.getAllTotalTransaction();
}*/
        List<TransactionModel> transactionModels = (List<TransactionModel>) conversionService.convert(transaction,
                TypeDescriptor.forObject(transaction),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(TransactionModel.class)));
        return transactionModels;
    }
}
