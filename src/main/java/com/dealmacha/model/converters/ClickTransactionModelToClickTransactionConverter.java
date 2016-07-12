package com.dealmacha.model.converters;

import com.dealmacha.domain.ClickTransaction;
import com.dealmacha.model.ClickTransactionModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("clickTransactionModelToClickTransactionConverter")
public class ClickTransactionModelToClickTransactionConverter implements Converter<ClickTransactionModel, ClickTransaction> {
    @Autowired
    private ObjectFactory<ClickTransaction> clickTransactionFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public ClickTransaction convert(final ClickTransactionModel source) {
        ClickTransaction clickTransaction = clickTransactionFactory.getObject();
        BeanUtils.copyProperties(source, clickTransaction);

        return clickTransaction;
    }

    @Autowired
    public void setClickTransactionFactory(final ObjectFactory<ClickTransaction> clickTransactionFactory) {
        this.clickTransactionFactory = clickTransactionFactory;
    }

}
