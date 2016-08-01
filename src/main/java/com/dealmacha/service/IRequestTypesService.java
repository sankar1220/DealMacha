package com.dealmacha.service;

import java.util.List;
import java.util.Set;

import com.dealmacha.domain.RequestTypeAttributes;
import com.dealmacha.domain.RequestTypes;

public interface IRequestTypesService {
	RequestTypes create(RequestTypes requestTypes);

    void deleteRequestTypes(String requestTypesId);

    RequestTypes getRequestTypes(String requestTypesId);

    List<RequestTypes> getAll();

    RequestTypes updateRequestTypes(RequestTypes requestTypes);

    RequestTypes addRequestTypeAttributes(RequestTypes requestTypes,
			Set<RequestTypeAttributes> requestTypeAttributeses);
}