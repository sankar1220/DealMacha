package com.dealmacha.service;

import java.util.List;

import com.dealmacha.domain.Account;
import com.dealmacha.domain.Requests;

public interface IRequestsService {
	Requests create(Requests requests);

    void deleteRequests(String requestsId);

    
    Requests getRequests(String requestsId);

    List<Requests> getAll();

   

    Requests updateRequests(Requests requests);

	
}
