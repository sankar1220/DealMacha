package com.dealmacha.model.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.Requests;
import com.dealmacha.model.RequestsModel;

@Component("requestsToRequestsModelConverter")
public class RequestsToRequestsModelConverter implements Converter<Requests, RequestsModel> {
    @Autowired
    private ObjectFactory<RequestsModel> requestsModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public RequestsModel convert(final Requests source) {
    	RequestsModel requestsModel = requestsModelFactory.getObject();
        BeanUtils.copyProperties(source, requestsModel);
        if(source.getId()!=null){
        	requestsModel.setRequestTypeName(source.getRequestTypes().getRequestType());
        }
        if(source.getUsers()!=null){
        	requestsModel.setUserName(source.getUsers().getUserName());
        }
        return requestsModel;
    }

    @Autowired
    public void setRequestsModelFactory(final ObjectFactory<RequestsModel> requestsModelFactory) {
        this.requestsModelFactory = requestsModelFactory;
    }

}