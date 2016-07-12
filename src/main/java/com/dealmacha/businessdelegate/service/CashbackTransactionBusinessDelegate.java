
package com.dealmacha.businessdelegate.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;




import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.CashbackTransaction;
import com.dealmacha.domain.Users;
import com.dealmacha.model.AddressModel;
import com.dealmacha.model.CashbackTransactionModel;
import com.dealmacha.model.ClickTransactionModel;
import com.dealmacha.model.UsersModel;
import com.dealmacha.service.CashbackTransactionService;
import com.dealmacha.service.ClickTransactionService;

@Service
public class CashbackTransactionBusinessDelegate implements
        IBusinessDelegate<CashbackTransactionModel, CashbackTransactionContext, IKeyBuilder<String>, String> {

    Logger LOGGER = Logger.getLogger(CashbackTransactionBusinessDelegate.class);
    @Autowired
    private CashbackTransactionService cashbackTransactionService;
    @Autowired
    private ConversionService conversionService;
	@Override
	public CashbackTransactionModel create(CashbackTransactionModel model) {
		CashbackTransaction cashbackTransaction=new CashbackTransaction();
		cashbackTransaction.setDate(new Date());		
		cashbackTransaction.setLongDescription(model.getLongDescription());
		cashbackTransaction.setShortDescription(model.getShortDescription());
		cashbackTransaction.setTransactionType(model.getTransactionType());
		Users users=new Users();
		users.setId(model.getUsersId());
		cashbackTransaction.setUsers(users);
		cashbackTransaction = cashbackTransactionService.create(cashbackTransaction);
	        model = conversionService.convert(cashbackTransaction, CashbackTransactionModel.class);
return model;
	}
	@Override
	public void delete(IKeyBuilder<String> keyBuilder,
			CashbackTransactionContext context) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public CashbackTransactionModel edit(IKeyBuilder<String> keyBuilder,
			CashbackTransactionModel model) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CashbackTransactionModel getByKey(IKeyBuilder<String> keyBuilder,
			CashbackTransactionContext context) {
		CashbackTransaction cashbackTransaction=cashbackTransactionService.getCashbackTransaction((keyBuilder.build().toString()));
		CashbackTransactionModel cashbackTransactionModel = conversionService.convert(cashbackTransaction, CashbackTransactionModel.class);
        return cashbackTransactionModel;
	}
	@Override
	public Collection<CashbackTransactionModel> getCollection(
			CashbackTransactionContext context) {
	List<CashbackTransaction> cashbackTransaction=new ArrayList<CashbackTransaction>();
	if(context.getAll()!=null){
		cashbackTransaction=cashbackTransactionService.getAll();
	}
	if(context.getUsersId()!=null){
		cashbackTransaction=cashbackTransactionService.getUserCashbackTransaction(context.getUsersId());
	}
	List<CashbackTransactionModel> cashbackTransactionModels = (List<CashbackTransactionModel>) conversionService.convert(cashbackTransaction, TypeDescriptor.forObject(cashbackTransaction),
            TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(CashbackTransactionModel.class)));
    return cashbackTransactionModels;
	}

    
    
}
