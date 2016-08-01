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

import com.dealmacha.domain.RequestTypeAttributes;
import com.dealmacha.domain.RequestTypes;
import com.dealmacha.domain.Requests;
import com.dealmacha.model.RequestTypesModel;
import com.dealmacha.util.CollectionTypeDescriptor;

@Component("requestTypesModelToRequestTypesConverter")
public class RequestTypesModelToRequestTypesConverter implements Converter<RequestTypesModel, RequestTypes> {
    @Autowired
    private ObjectFactory<RequestTypes> requestTypesFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public RequestTypes convert(final RequestTypesModel source) {
    	RequestTypes requestTypes= requestTypesFactory.getObject();
        BeanUtils.copyProperties(source, requestTypes);

        if (CollectionUtils.isNotEmpty(source.getRequestsModels())) {
            List<Requests> converted = (List<Requests>) conversionService.convert(source.getRequestsModels(),
                    TypeDescriptor.forObject(source.getRequestsModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), Requests.class));
            requestTypes.getRequestses().addAll(converted);
        }

        if (CollectionUtils.isNotEmpty(source.getRequestTypeAttributesModels())) {
            List<RequestTypeAttributes> converted = (List<RequestTypeAttributes>) conversionService.convert(source.getRequestTypeAttributesModels(),
                    TypeDescriptor.forObject(source.getRequestTypeAttributesModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), RequestTypeAttributes.class));
            requestTypes.getRequestTypeAttributeses().addAll(converted);
        }

        return requestTypes;
    }

    @Autowired
    public void setRequestTypesFactory(final ObjectFactory<RequestTypes> requestTypesFactory) {
        this.requestTypesFactory = requestTypesFactory;
    }

}
