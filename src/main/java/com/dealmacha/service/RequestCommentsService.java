package com.dealmacha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dealmacha.dao.RequestCommentsRepository;
import com.dealmacha.domain.RequestComments;

@Component
public class RequestCommentsService implements IRequestCommentsService {
    @Autowired
    private RequestCommentsRepository requestCommentsRepository;

	@Override
	public RequestComments create(RequestComments requestComments) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRequestComments(String requestCommentsId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RequestComments getRequestComments(String requestCommentsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequestComments> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestComments updateRequestComments(RequestComments requestComments) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
