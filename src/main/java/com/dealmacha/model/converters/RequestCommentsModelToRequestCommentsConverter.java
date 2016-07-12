package com.dealmacha.model.converters;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.RequestComments;
import com.dealmacha.model.RequestCommentsModel;

@Component("requestCommentsModelToRequestCommentsConverter")
public class RequestCommentsModelToRequestCommentsConverter implements Converter<RequestCommentsModel, RequestComments> {
    @Autowired
    private ObjectFactory<RequestComments> requestCommentsFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public RequestComments convert(final RequestCommentsModel source) {
    	RequestComments requestComments= requestCommentsFactory.getObject();
        BeanUtils.copyProperties(source, requestComments);

       

        return requestComments;
    }

    @Autowired
    public void setRequestCommentsFactory(final ObjectFactory<RequestComments> requestCommentsFactory) {
        this.requestCommentsFactory = requestCommentsFactory;
    }

}
