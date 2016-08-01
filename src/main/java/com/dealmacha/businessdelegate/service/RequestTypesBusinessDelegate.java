package com.dealmacha.businessdelegate.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.RequestTypeAttributes;
import com.dealmacha.domain.RequestTypes;
import com.dealmacha.model.RequestTypeAttributesModel;
import com.dealmacha.model.RequestTypesModel;
import com.dealmacha.service.IRequestTypeAttributesService;
import com.dealmacha.service.IRequestTypesService;
@Service
public class RequestTypesBusinessDelegate  implements IBusinessDelegate<RequestTypesModel, RequestTypesContext, IKeyBuilder<String>, String> {
    Logger LOGGER = Logger.getLogger(RequestTypesBusinessDelegate.class);
    @Autowired
    private IRequestTypesService requestTypesService;
    @Autowired
    private IRequestTypeAttributesService requestTypeAttributesService;
    @Autowired
    private ConversionService conversionService;
  
	@Override
	public RequestTypesModel create(RequestTypesModel model) {

		RequestTypes requestTypes = new RequestTypes();
		requestTypes.setRequestType(model.getRequestType());
		requestTypes.setRequestDescription(model.getRequestDescription());
		requestTypes.setStatus("ACTIVE");
		requestTypes.setCreatedDate(new Date());
		requestTypes = requestTypesService.create(requestTypes);
		if(requestTypes.getId()!=null){
			Set<RequestTypeAttributes> requestTypeAttributeses = new HashSet<RequestTypeAttributes>();
			if(model.getRequestTypeAttributesModels()!=null){
			for(RequestTypeAttributesModel requestTypeAttributesModel :model.getRequestTypeAttributesModels()){
				RequestTypeAttributes requestTypeAttributes = new RequestTypeAttributes();
				requestTypeAttributes.setId(requestTypeAttributesModel.getId());
				requestTypeAttributes.setStatus("ACTIVE");
				requestTypeAttributes.setRequestTypeAttribute(requestTypeAttributesModel.getRequestTypeAttribute());
				requestTypeAttributes.setRequestTypes(requestTypes);
				requestTypeAttributeses.add(requestTypeAttributes);
			}
			requestTypes = requestTypesService.addRequestTypeAttributes(requestTypes, requestTypeAttributeses);
		}
		}
		model.setId(requestTypes.getId());
		return model;
	}
	@Override
	public void delete(IKeyBuilder<String> keyBuilder,
			RequestTypesContext context) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public RequestTypesModel edit(IKeyBuilder<String> keyBuilder,
			RequestTypesModel model) {
		RequestTypes requestTypes = requestTypesService.getRequestTypes(keyBuilder.build().toString());
		RequestTypes requestType = requestTypes;
		if(model.getRequestType()!=null){
		requestType.setRequestType(model.getRequestType());
		}
		if(model.getRequestDescription()!=null){
		requestType.setRequestDescription(model.getRequestDescription());
		}
		if(model.getStatus()!=null){
		requestType.setStatus(model.getStatus());
		}
		requestTypes = requestTypesService.updateRequestTypes(requestType);
		if (requestTypes.getId() != null) {
	            Set<RequestTypeAttributes> requestTypeAttributes = new HashSet<RequestTypeAttributes>();
	            if (CollectionUtils.isNotEmpty(model.getRequestTypeAttributesModels())) {
	                for (RequestTypeAttributesModel requestTypeAttributesModel : model.getRequestTypeAttributesModels()) {
	                	RequestTypeAttributes requestTypeAttribute = new RequestTypeAttributes();
	                	requestTypeAttribute.setId(requestTypeAttributesModel.getId());
	                	requestTypeAttribute.setStatus(requestTypeAttributesModel.getStatus());
	                	requestTypeAttribute.setRequestTypeAttribute(requestTypeAttributesModel.getRequestTypeAttribute());	                	
	                	requestTypeAttribute.setRequestTypes(requestTypes);	                   
	                	requestTypeAttributes.add(requestTypeAttribute);
	                }
	                requestType = requestTypesService.addRequestTypeAttributes(requestTypes, requestTypeAttributes);
	            }
	        }
		model.setId(requestTypes.getId());
		return model;
	}
	@Override
	public RequestTypesModel getByKey(IKeyBuilder<String> keyBuilder,
			RequestTypesContext context) {
		RequestTypes requestTypes = requestTypesService.getRequestTypes(keyBuilder.build().toString());
		RequestTypesModel requestTypesModel = conversionService.convert(requestTypes, RequestTypesModel.class);
	        return requestTypesModel;
	}
	@Override
	public Collection<RequestTypesModel> getCollection(
			RequestTypesContext context) {
		 List<RequestTypes> requestTypes = new ArrayList<RequestTypes>();
	        if (context.getAll() != null) {
	        	requestTypes = requestTypesService.getAll();
	        }
	        List<RequestTypesModel> requestTypesModels = (List<RequestTypesModel>) conversionService.convert(requestTypes, TypeDescriptor.forObject(requestTypes),
	                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(RequestTypesModel.class)));
	        return requestTypesModels;
	    }
}
