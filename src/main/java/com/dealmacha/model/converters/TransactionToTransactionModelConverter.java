package com.dealmacha.model.converters;

import com.dealmacha.domain.Transaction;
import com.dealmacha.model.PaymentModel;
import com.dealmacha.model.TransactionModel;
import com.dealmacha.util.CollectionTypeDescriptor;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("transactionToTransactionModelConverter")
public class TransactionToTransactionModelConverter implements Converter<Transaction, TransactionModel> {
    @Autowired
    private ObjectFactory<TransactionModel> transactionModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public TransactionModel convert(final Transaction source) {
        TransactionModel transactionModel = transactionModelFactory.getObject();
        BeanUtils.copyProperties(source, transactionModel);
        if(source.getUsers().getId()!=null){
        transactionModel.setUsersId(source.getUsers().getId());
        transactionModel.setUserName(source.getUsers().getUserName());
        }
        if(source.getId()!=null){
      //  transactionModel.setAmount(source.getAmount().toString());
      //  transactionModel.setCashbackAmount(source.getCashbackAmount().toString());
      
        }
       
       /* if (source.getId() != null) {
            transactionModel.setAmount(Double.toString(source.getAmount()));
        }
        if (source.getId() != null) {
            transactionModel.setCashbackAmount(Double.toString(source.getCashbackAmount()));
        }
        if (source.getId() != null) {
            transactionModel.setUserName(source.getUsers().getUserName());
        }*/
       
        transactionModel.setMerchantId(source.getMerchant().getId());

        String ds1 = null;
        if (source.getCreatedDate() != null) {
            ds1 = source.getCreatedDate().toString();
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        if (ds1 != null) {
            try {
                String ds2 = sdf2.format(source.getCreatedDate());
                transactionModel.setCreatedDate(ds2);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (source.getTransactionCount() != null) {
            transactionModel.setTransactionCount(Integer.toString(source.getTransactionCount()));
        }
        if (source.getSource() != null) {
            transactionModel.setSource(source.getSource());
        }
        if (CollectionUtils.isNotEmpty(source.getPayments())) {
            List<PaymentModel> converted = (List<PaymentModel>) conversionService.convert(source.getPayments(),
                    TypeDescriptor.forObject(source.getPayments()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), PaymentModel.class));
            transactionModel.getPaymentModels().addAll(converted);
        }

        return transactionModel;
    }

    @Autowired
    public void setTransactionModelFactory(final ObjectFactory<TransactionModel> transactionModelFactory) {
        this.transactionModelFactory = transactionModelFactory;
    }

}