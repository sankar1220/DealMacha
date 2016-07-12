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
import com.dealmacha.domain.Requests;
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.RequestCommentsModel;
import com.dealmacha.model.RequestsModel;
import com.dealmacha.util.CollectionTypeDescriptor;

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
        if (CollectionUtils.isNotEmpty(source.getRequestComments())) {
            List<RequestCommentsModel> converted = (List<RequestCommentsModel>) conversionService.convert(source.getRequestComments(),
                    TypeDescriptor.forObject(source.getRequestComments()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), RequestCommentsModel.class));
            requestsModel.getRequestCommentsModels().addAll(converted);
        }
        return requestsModel;
    }

    @Autowired
    public void setRequestsModelFactory(final ObjectFactory<RequestsModel> requestsModelFactory) {
        this.requestsModelFactory = requestsModelFactory;
    }

}