package com.dealmacha.service;

import java.util.List;

import com.dealmacha.domain.RequestTypeAttributes;

public interface IRequestTypeAttributesService {
	
	RequestTypeAttributes create(RequestTypeAttributes requestTypeAttributes);

    void deleteRequestTypeAttributes(String requestTypeAttributesId);

    RequestTypeAttributes getRequestTypeAttributes(String requestTypeAttributesId);

    List<RequestTypeAttributes> getAll();

    RequestTypeAttributes updateRequestTypeAttributes(RequestTypeAttributes requestTypeAttributes);
}