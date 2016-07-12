package com.dealmacha.model.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.CashbackTransaction;
import com.dealmacha.model.CashbackTransactionModel;

@Component("cashbackTransactionModelToCashbackTransactionConverter")
public class CashbackTransactionModelToCashbackTransactionConverter implements Converter<CashbackTransactionModel, CashbackTransaction> {
    @Autowired
    private ObjectFactory<CashbackTransaction> cashbackTransactionFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public CashbackTransaction convert(final CashbackTransactionModel source) {
        CashbackTransaction cashbackTransaction = cashbackTransactionFactory.getObject();
        BeanUtils.copyProperties(source, cashbackTransaction);

        return cashbackTransaction;
    }

    @Autowired
    public void setCashbackTransactionFactory(final ObjectFactory<CashbackTransaction> cashbackTransactionFactory) {
        this.cashbackTransactionFactory = cashbackTransactionFactory;
    }

}
