package com.dealmacha.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dealmacha.dao.AddressRepository;
import com.dealmacha.dao.RequestsRepository;
import com.dealmacha.domain.Requests;

@Component
public class RequestsService implements IRequestsService {

    private static final Logger LOGGER = Logger.getLogger(RequestsService.class);
    @Autowired
    private RequestsRepository requestsRepository;
	@Override
	public Requests create(Requests requests) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteRequests(String requestsId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Requests getRequests(String requestsId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Requests> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Requests updateRequests(Requests requests) {
		// TODO Auto-generated method stub
		return null;
	}
    

}
