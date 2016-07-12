package com.dealmacha.businessdelegate.service;

import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.Requests;
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.RequestsModel;
import com.dealmacha.service.IAccountService;
import com.dealmacha.service.IRequestsService;
@Service
public class RequestsBusinessDelegate implements IBusinessDelegate<RequestsModel, RequestsContext, IKeyBuilder<String>, String> {

    Logger LOGGER = Logger.getLogger(RequestsBusinessDelegate.class);
    //private IEntityService entityService;
    //private IEntityBranchService entityBranchService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private IRequestsService requestsService;
	@Override
	public RequestsModel create(RequestsModel model) {
		Requests requests=new Requests();
		requests.setCreatedDate(new Date());
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(IKeyBuilder<String> keyBuilder, RequestsContext context) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public RequestsModel edit(IKeyBuilder<String> keyBuilder,
			RequestsModel model) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RequestsModel getByKey(IKeyBuilder<String> keyBuilder,
			RequestsContext context) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<RequestsModel> getCollection(RequestsContext context) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
