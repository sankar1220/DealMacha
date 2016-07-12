package com.dealmacha.model.converters;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.Account;
import com.dealmacha.domain.RequestComments;
import com.dealmacha.domain.Requests;
import com.dealmacha.domain.Roles;
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.RequestsModel;
import com.dealmacha.util.CollectionTypeDescriptor;

@Component("requestsModelToRequestsConverter")
public class RequestsModelToRequestsConverter implements Converter<RequestsModel, Requests> {
    @Autowired
    private ObjectFactory<Requests> requestsFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public Requests convert(final RequestsModel source) {
    	Requests requests= requestsFactory.getObject();
        BeanUtils.copyProperties(source, requests);
       if (CollectionUtils.isNotEmpty(source.getRequestCommentsModels())) {
            List<RequestComments> converted = (List<RequestComments>) conversionService.convert(source.getRequestCommentsModels(),
                    TypeDescriptor.forObject(source.getRequestCommentsModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), RequestComments.class));
            requests.getRequestComments().addAll(converted);
        }

        return requests;
    }

    @Autowired
    public void setRequestsFactory(final ObjectFactory<Requests> requestsFactory) {
        this.requestsFactory = requestsFactory;
    }

}