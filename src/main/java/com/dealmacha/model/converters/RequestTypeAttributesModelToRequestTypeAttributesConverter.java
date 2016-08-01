package com.dealmacha.model.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.RequestTypeAttributes;
import com.dealmacha.model.RequestTypeAttributesModel;

	@Component("requestTypeAttributesModelToRequestTypeAttributesConverter")
	public class RequestTypeAttributesModelToRequestTypeAttributesConverter implements Converter<RequestTypeAttributesModel, RequestTypeAttributes> {
	    @Autowired
	    private ObjectFactory<RequestTypeAttributes> requestTypeAttributesFactory;
	    @Autowired
	    private ConversionService conversionService;

	    @Override
	    public RequestTypeAttributes convert(final RequestTypeAttributesModel source) {
	    	RequestTypeAttributes requestTypeAttributes = requestTypeAttributesFactory.getObject();
	        BeanUtils.copyProperties(source, requestTypeAttributes);
	        return requestTypeAttributes;
	    }

	    @Autowired
	    public void setRequestTypeAttributesFactory(final ObjectFactory<RequestTypeAttributes> requestTypeAttributesFactory) {
	        this.requestTypeAttributesFactory = requestTypeAttributesFactory;
	    }
}
