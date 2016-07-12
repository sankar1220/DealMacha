package com.dealmacha.service;

import java.util.List;

import com.dealmacha.domain.RequestComments;

public interface IRequestCommentsService {
	RequestComments create(RequestComments requestComments);

    void deleteRequestComments(String requestCommentsId);

    RequestComments getRequestComments(String requestCommentsId);

    List<RequestComments> getAll();



    RequestComments updateRequestComments(RequestComments requestComments);
}