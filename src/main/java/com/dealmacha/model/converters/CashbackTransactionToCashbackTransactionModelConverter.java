package com.dealmacha.model.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.CashbackTransaction;
import com.dealmacha.model.CashbackTransactionModel;

@Component("cashbackTransactionToCashbackTransactionModelConverter")
public class CashbackTransactionToCashbackTransactionModelConverter implements Converter<CashbackTransaction, CashbackTransactionModel> {
    @Autowired
    private ObjectFactory<CashbackTransactionModel> cashbackTransactionModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public CashbackTransactionModel convert(final CashbackTransaction source) {
    	CashbackTransactionModel cashbackTransactionModel = cashbackTransactionModelFactory.getObject();
        BeanUtils.copyProperties(source, cashbackTransactionModel);
        String ds1 = null;
        if (source.getDate() != null) {
            ds1 = source.getDate().toString();
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        if (ds1 != null) {
            try {
                String ds2 = sdf2.format(source.getDate());
                cashbackTransactionModel.setDate(ds2);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
       if(source.getId()!=null){
    	   cashbackTransactionModel.setUsersId(source.getUsers().getId());
    	   cashbackTransactionModel.setUserName(source.getUsers().getUserName());
       }
       cashbackTransactionModel.setId(source.getId());
        return cashbackTransactionModel;
    }

    @Autowired
    public void setCashbackTransactionModelFactory(final ObjectFactory<CashbackTransactionModel> cashbackTransactionModelFactory) {
        this.cashbackTransactionModelFactory = cashbackTransactionModelFactory;
    }
}
