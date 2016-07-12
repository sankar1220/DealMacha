package com.dealmacha.businessdelegate.service;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.model.RequestCommentsModel;
import com.dealmacha.service.RequestCommentsService;
@Service
public class RequestCommentsBusinessDelegate  implements IBusinessDelegate<RequestCommentsModel, RequestCommentsContext, IKeyBuilder<String>, String> {
    Logger LOGGER = Logger.getLogger(RequestCommentsBusinessDelegate.class);
    @Autowired
    private RequestCommentsService requestCommentsService;
    @Autowired
    private ConversionService conversionService;
	@Override
	public RequestCommentsModel create(RequestCommentsModel model) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(IKeyBuilder<String> keyBuilder,
			RequestCommentsContext context) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public RequestCommentsModel edit(IKeyBuilder<String> keyBuilder,
			RequestCommentsModel model) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public RequestCommentsModel getByKey(IKeyBuilder<String> keyBuilder,
			RequestCommentsContext context) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<RequestCommentsModel> getCollection(
			RequestCommentsContext context) {
		// TODO Auto-generated method stub
		return null;
	}
    

}
