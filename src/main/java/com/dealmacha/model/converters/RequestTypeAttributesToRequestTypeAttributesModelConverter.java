package com.dealmacha.model.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.RequestTypeAttributes;
import com.dealmacha.model.RequestTypeAttributesModel;
import com.dealmacha.model.RequestsModel;

	@Component("requestTypeAttributesToRequestTypeAttributesModelConverter")
	public class RequestTypeAttributesToRequestTypeAttributesModelConverter implements Converter<RequestTypeAttributes, RequestTypeAttributesModel> {
	    @Autowired
	    private ObjectFactory<RequestTypeAttributesModel> requestTypeAttributesModelFactory;
	    @Autowired
	    private ConversionService conversionService;

	    @Override
	    public RequestTypeAttributesModel convert(final RequestTypeAttributes source) {
	    	RequestTypeAttributesModel requestTypeAttributesModel = requestTypeAttributesModelFactory.getObject();
	        BeanUtils.copyProperties(source, requestTypeAttributesModel);
	        return requestTypeAttributesModel;
	    }

	    @Autowired
	    public void setRequestTypeAttributesModelFactory(final ObjectFactory<RequestsModel> requestsTypeAttriobutesModelFactory) {
	        this.requestTypeAttributesModelFactory = requestTypeAttributesModelFactory;
	    }

}
