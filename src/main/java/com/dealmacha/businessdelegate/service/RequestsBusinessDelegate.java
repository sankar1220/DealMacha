package com.dealmacha.businessdelegate.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.RequestTypes;
import com.dealmacha.domain.Requests;
import com.dealmacha.domain.Users;
import com.dealmacha.model.RequestsModel;
import com.dealmacha.security.CustomUserDetails;
import com.dealmacha.service.IRequestsService;

@Service
public class RequestsBusinessDelegate
		implements IBusinessDelegate<RequestsModel, RequestsContext, IKeyBuilder<String>, String> {

	Logger LOGGER = Logger.getLogger(RequestsBusinessDelegate.class);
	// private IEntityService entityService;
	// private IEntityBranchService entityBranchService;
	@Autowired
	private ConversionService conversionService;
	@Autowired
	private IRequestsService requestsService;

	@Override
	public RequestsModel create(RequestsModel model) {
		Requests requests = new Requests();
		
		requests.setRequestComments(model.getRequestComments());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		   CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		   if(userDetails.getId()!=null){
			   Users users = new Users();
			   users.setId(userDetails.getId());
			   requests.setUsers(users);
		   }
		   
		   requests.setRequestType(model.getRequestType());
		   if(model.getRequestTypesId()!=null){
		   RequestTypes requestType = new RequestTypes();
		   requestType.setId(model.getRequestTypesId());
		   requests.setRequestTypes(requestType);
		   }
		requests.setCreatedDate(new Date());
		requests.setRequestStatus("ACTIVE");
		requests = requestsService.create(requests);
		
		model.setId(requests.getId());
		return model;
	}

	@Override
	public void delete(IKeyBuilder<String> keyBuilder, RequestsContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public RequestsModel edit(IKeyBuilder<String> keyBuilder, RequestsModel model) {
		Requests requests = requestsService.getRequests(keyBuilder.build().toString());
		requests.setModifiedDate(new Date());
		requests.setRequestStatus(model.getRequestStatus());
		requests = requestsService.updateRequests(requests);
		
		model.setId(requests.getId());
		return model;
	}

	@Override
	public RequestsModel getByKey(IKeyBuilder<String> keyBuilder, RequestsContext context) {
		Requests requests = requestsService.getRequests(keyBuilder.build().toString());
		RequestsModel requestsModel = conversionService.convert(requests, RequestsModel.class);
	        return requestsModel;
	}

	@Override
	public Collection<RequestsModel> getCollection(RequestsContext context) {
		 List<Requests> requests = new ArrayList<Requests>();
	        if (context.getAll() != null) {
	        	requests = requestsService.getAll();
	        }
	        if(context.getUsersId()!=null){
	        	requests = requestsService.getRequestsByUsers(context.getUsersId());
	        }
	        List<RequestsModel> requestsModels = (List<RequestsModel>) conversionService.convert(requests, TypeDescriptor.forObject(requests),
	                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(RequestsModel.class)));
	        return requestsModels;
	    }

}
