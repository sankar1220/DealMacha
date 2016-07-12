package com.dealmacha.model.converters;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.Address;
import com.dealmacha.domain.ClickTransaction;

import com.dealmacha.model.ClickTransactionModel;

import com.dealmacha.util.CollectionTypeDescriptor;

	@Component("clickTransactionToClickTransactionModelConverter")
	public class ClickTransactionToClickTransactionModelConverter implements Converter<ClickTransaction, ClickTransactionModel> {
	    @Autowired
	    private ObjectFactory<ClickTransactionModel> clickTransactionModelFactory;
	    @Autowired
	    private ConversionService conversionService;

	    @Override
	    public ClickTransactionModel convert(final ClickTransaction source) {
	    	ClickTransactionModel clickTransactionModel = clickTransactionModelFactory.getObject();
	        BeanUtils.copyProperties(source, clickTransactionModel);

	       

	        return clickTransactionModel;
	    }

	    @Autowired
	    public void setClickTransactionModelFactory(final ObjectFactory<ClickTransactionModel> clickTransactionModelFactory) {
	        this.clickTransactionModelFactory = clickTransactionModelFactory;
	    }
}
