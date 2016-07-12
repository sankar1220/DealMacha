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

import com.dealmacha.domain.Account;
import com.dealmacha.domain.Address;
import com.dealmacha.domain.UserAddress;
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.AddressModel;
import com.dealmacha.util.CollectionTypeDescriptor;

@Component("accountModelToAccountConverter")
public class AccountModelToAccountConverter implements Converter<AccountModel, Account> {
    @Autowired
    private ObjectFactory<Account> accountFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public Account convert(final AccountModel source) {
    	Account account = accountFactory.getObject();
        BeanUtils.copyProperties(source, account);


        return account;
    }

    @Autowired
    public void setAccountFactory(final ObjectFactory<Account > accountFactory) {
        this.accountFactory = accountFactory;
    }

}