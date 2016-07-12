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
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.AddressModel;
import com.dealmacha.model.UserAddressModel;
import com.dealmacha.util.CollectionTypeDescriptor;

@Component("accountToAccountModelConverter")
public class AccountToAccountModelConverter implements Converter<Account, AccountModel> {
    @Autowired
    private ObjectFactory<AccountModel> accountModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public AccountModel convert(final Account source) {
    	AccountModel accountModel = accountModelFactory.getObject();
        BeanUtils.copyProperties(source, accountModel);
if(source.getId()!=null){
	accountModel.setUsersId(source.getUsers().getId());
}
accountModel.setCashbackAmount(source.getCashbackAmount().toString());
       
        return accountModel;
    }

    @Autowired
    public void setAccountModelFactory(final ObjectFactory<AccountModel> accountModelFactory) {
        this.accountModelFactory = accountModelFactory;
    }

}