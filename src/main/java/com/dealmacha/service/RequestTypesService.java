package com.dealmacha.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.Validate;

import com.dealmacha.dao.RequestTypesRepository;
import com.dealmacha.domain.RequestTypeAttributes;
import com.dealmacha.domain.RequestTypes;

@Component
public class RequestTypesService implements IRequestTypesService {
	@Autowired
	private RequestTypesRepository requestTypesRepository;
	  @Autowired
	    private IRequestTypeAttributesService  requestTypeAttributesService;
	@Override
	public RequestTypes create(RequestTypes requestTypes) {
		// TODO Auto-generated method stub
		return requestTypesRepository.save(requestTypes);
	}

	@Override
	public void deleteRequestTypes(String requestTypesId) {
		// TODO Auto-generated method stub

	}

	@Override
	public RequestTypes getRequestTypes(String requestTypesId) {
		// TODO Auto-generated method stub
		return requestTypesRepository.findOne(requestTypesId);
	}

	@Override
	public List<RequestTypes> getAll() {
		// TODO Auto-generated method stub
		return (List<RequestTypes>) requestTypesRepository.findAll();
	}

	@Override
	public RequestTypes updateRequestTypes(RequestTypes requestTypes) {
		// TODO Auto-generated method stub
		return requestTypesRepository.save(requestTypes);
	}

	@Override
	@Transactional
	public RequestTypes addRequestTypeAttributes(RequestTypes requestTypes,
			Set<RequestTypeAttributes> requestTypeAttributeses) {
		Validate.notNull(requestTypes, "requestTypes must not be null");
		 Set<RequestTypeAttributes> addreqTypeAttributes = new HashSet<RequestTypeAttributes>(requestTypeAttributeses);
		 Set<RequestTypeAttributes> requestTypeAttributses = new HashSet<RequestTypeAttributes>();
	        for (RequestTypeAttributes reqTypeAttr : addreqTypeAttributes) {
	        	RequestTypeAttributes requestTypeAttributes = new RequestTypeAttributes();
	            if (reqTypeAttr.getId() != null) {
	                requestTypeAttributes = requestTypeAttributesService.getRequestTypeAttributes(reqTypeAttr.getId());
	                requestTypeAttributes.setId(reqTypeAttr.getId());
	                requestTypeAttributes.setRequestTypeAttribute(reqTypeAttr.getRequestTypeAttribute());
	                requestTypeAttributes.setStatus(reqTypeAttr.getStatus());
	                requestTypeAttributes.setRequestTypes(requestTypeAttributes.getRequestTypes());
	                requestTypeAttributes = requestTypeAttributesService.updateRequestTypeAttributes(requestTypeAttributes);
	                requestTypeAttributses.add(requestTypeAttributes);
	                
	            }
	            requestTypeAttributes.setRequestTypeAttribute(reqTypeAttr.getRequestTypeAttribute());
                requestTypeAttributes.setStatus(reqTypeAttr.getStatus());
                requestTypeAttributes.setRequestTypes(reqTypeAttr.getRequestTypes());
	            requestTypeAttributes = requestTypeAttributesService.create(requestTypeAttributes);
	            requestTypeAttributses.add(requestTypeAttributes);
	        }
	        requestTypes.setRequestTypeAttributeses(requestTypeAttributses);
	        return requestTypes;
	}

}
