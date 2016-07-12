package com.dealmacha.businessdelegate.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;

import com.dealmacha.dao.TransactionRepository;
import com.dealmacha.dao.UsersRepository;
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.HomeModel;
import com.dealmacha.model.UsersModel;

@Service
public class HomeBusinessDelegate implements IBusinessDelegate<HomeModel, HomeContext, IKeyBuilder<String>, String> {

    Logger LOGGER = Logger.getLogger(HomeBusinessDelegate.class);
    //private IEntityService entityService;
    //private IEntityBranchService entityBranchService;
    @Autowired
    private ConversionService conversionService;
  
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    
	@Override
	public HomeModel create(HomeModel model) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(IKeyBuilder<String> keyBuilder, HomeContext context) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public HomeModel edit(IKeyBuilder<String> keyBuilder, HomeModel model) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HomeModel getByKey(IKeyBuilder<String> keyBuilder,
			HomeContext context) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<HomeModel> getCollection(HomeContext context) {

	
	HomeModel model = new HomeModel();
	
	 if( context.getDashboardData()!=null) {		 
     	model.setActivatedUsers(usersRepository.getActiveUsers().toString());
     	model.setTotalTransactions(transactionRepository.getTotalTransaction().toString());
     	model.setCommissionReceived(transactionRepository.getCommissionReceived().toString());     	
     }
	 
	 List<HomeModel> homeModels = (List<HomeModel>) conversionService.convert(model, TypeDescriptor.forObject(model),
             TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(HomeModel.class)));
     return homeModels;
	}
    

}
