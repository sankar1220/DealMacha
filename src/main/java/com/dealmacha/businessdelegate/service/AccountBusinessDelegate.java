package com.dealmacha.businessdelegate.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.Account;
import com.dealmacha.domain.Transaction;
import com.dealmacha.domain.Users;
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.AddressModel;
import com.dealmacha.model.TransactionModel;
import com.dealmacha.service.IAccountService;
import com.dealmacha.service.IAddressService;

@Service
public class AccountBusinessDelegate implements IBusinessDelegate<AccountModel, AccountContext, IKeyBuilder<String>, String> {

    Logger LOGGER = Logger.getLogger(AccountBusinessDelegate.class);
    //private IEntityService entityService;
    //private IEntityBranchService entityBranchService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private IAccountService accountService;
	@Override
	public AccountModel create(AccountModel model) {	

      return null;
	}
	@Override
	public void delete(IKeyBuilder<String> keyBuilder, AccountContext context) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public AccountModel edit(IKeyBuilder<String> keyBuilder, AccountModel model) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AccountModel getByKey(IKeyBuilder<String> keyBuilder,
			AccountContext context) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<AccountModel> getCollection(AccountContext context) {
		 List<Account> account = new ArrayList<Account>();
		 if (context.getUsersId() != null) {
			 account= accountService.getUsersAccount(context.getUsersId());
	        }
		 List<AccountModel> accountModels = (List<AccountModel>) conversionService.convert(account,
	                TypeDescriptor.forObject(account),
	                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(AccountModel.class)));
	        return accountModels;
	}
    
}
