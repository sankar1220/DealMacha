package com.dealmacha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealmacha.dao.RequestTypeAttributesRepository;
import com.dealmacha.domain.RequestTypeAttributes;

@Service
public class RequestTypeAttributesService implements IRequestTypeAttributesService {
	@Autowired
	private RequestTypeAttributesRepository requestTypeAttributesRepository;

	@Override
	public RequestTypeAttributes create(RequestTypeAttributes requestTypeAttributes) {
		return requestTypeAttributesRepository.save(requestTypeAttributes);
	}

	@Override
	public void deleteRequestTypeAttributes(String requestTypeAttributesId) {

	}

	@Override
	public RequestTypeAttributes getRequestTypeAttributes(String requestTypeAttributesId) {
		return requestTypeAttributesRepository.findOne(requestTypeAttributesId);
	}

	@Override
	public List<RequestTypeAttributes> getAll() {
		return (List<RequestTypeAttributes>) requestTypeAttributesRepository.findAll();
	}

	@Override
	public RequestTypeAttributes updateRequestTypeAttributes(RequestTypeAttributes requestTypeAttributes) {
		return requestTypeAttributesRepository.save(requestTypeAttributes);
	}

}
