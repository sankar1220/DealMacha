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

import com.dealmacha.domain.RequestTypes;
import com.dealmacha.model.RequestTypeAttributesModel;
import com.dealmacha.model.RequestTypesModel;
import com.dealmacha.model.RequestsModel;
import com.dealmacha.util.CollectionTypeDescriptor;

@Component("requestTypesToRequestTypesModelConverter")
public class RequestTypesToRequestTypesModelConverter implements Converter<RequestTypes, RequestTypesModel> {
    @Autowired
    private ObjectFactory<RequestTypesModel> requestTypesModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public RequestTypesModel convert(final RequestTypes source) {
    	RequestTypesModel requestTypesModel = requestTypesModelFactory.getObject();
        BeanUtils.copyProperties(source, requestTypesModel);

        if (CollectionUtils.isNotEmpty(source.getRequestses())) {
            List<RequestsModel> converted = (List<RequestsModel>) conversionService.convert(source.getRequestses(),
                    TypeDescriptor.forObject(source.getRequestses()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), RequestsModel.class));
            requestTypesModel.getRequestsModels().addAll(converted);
        }

        if (CollectionUtils.isNotEmpty(source.getRequestTypeAttributeses())) {
            List<RequestTypeAttributesModel> converted = (List<RequestTypeAttributesModel>) conversionService.convert(source.getRequestTypeAttributeses(),
                    TypeDescriptor.forObject(source.getRequestTypeAttributeses()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), RequestTypeAttributesModel.class));
            requestTypesModel.getRequestTypeAttributesModels().addAll(converted);
        }
        return requestTypesModel;
    }

    @Autowired
    public void setRequestTypesModelFactory(final ObjectFactory<RequestTypesModel> requestTypesModelFactory) {
        this.requestTypesModelFactory = requestTypesModelFactory;
    }

}