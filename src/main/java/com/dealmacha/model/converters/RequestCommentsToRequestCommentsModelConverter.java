package com.dealmacha.model.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.RequestComments;
import com.dealmacha.model.RequestCommentsModel;

@Component("requestCommentsToRequestCommentsModelConverter")
public class RequestCommentsToRequestCommentsModelConverter implements Converter<RequestComments, RequestCommentsModel> {
    @Autowired
    private ObjectFactory<RequestCommentsModel> requestCommentsModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public RequestCommentsModel convert(final RequestComments source) {
    	RequestCommentsModel requestCommentsModel = requestCommentsModelFactory.getObject();
        BeanUtils.copyProperties(source, requestCommentsModel);

        

        return requestCommentsModel;
    }

    @Autowired
    public void setRequestCommentsModelFactory(final ObjectFactory<RequestCommentsModel> requestCommentsModelFactory) {
        this.requestCommentsModelFactory = requestCommentsModelFactory;
    }

}