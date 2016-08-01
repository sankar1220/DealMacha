package com.dealmacha.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dealmacha.dao.RequestsRepository;
import com.dealmacha.domain.Requests;

@Component
public class RequestsService implements IRequestsService {

    private static final Logger LOGGER = Logger.getLogger(RequestsService.class);
    @Autowired
    private RequestsRepository requestsRepository;
	@Override
	public Requests create(Requests requests) {
		return requestsRepository.save(requests);
	}
	@Override
	public void deleteRequests(String requestsId) {
		
	}
	@Override
	public Requests getRequests(String requestsId) {
		return requestsRepository.findOne(requestsId);
	}
	@Override
	public List<Requests> getAll() {
		return (List<Requests>) requestsRepository.findAll();
	}
	@Override
	public Requests updateRequests(Requests requests) {
		return requestsRepository.save(requests);
	}
	@Override
	public List<Requests> getRequestsByUsers(String usersId) {
		// TODO Auto-generated method stub
		return requestsRepository.findRequestByUsers(usersId);
	}
    

}
