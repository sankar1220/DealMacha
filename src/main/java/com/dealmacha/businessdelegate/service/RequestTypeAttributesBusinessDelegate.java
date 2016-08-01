package com.dealmacha.businessdelegate.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.dao.RequestTypeAttributesRepository;
import com.dealmacha.domain.RequestTypeAttributes;
import com.dealmacha.model.RequestTypeAttributesModel;
import com.dealmacha.model.RequestsModel;

	@Service
	public class RequestTypeAttributesBusinessDelegate
			implements IBusinessDelegate<RequestTypeAttributesModel, RequestTypeAttributesContext, IKeyBuilder<String>, String> {

		@Autowired
		private RequestTypeAttributesRepository requestTypeAttributesRepository;
		@Override
		public RequestTypeAttributesModel create(RequestTypeAttributesModel model) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void delete(IKeyBuilder<String> keyBuilder, RequestTypeAttributesContext context) {
			RequestTypeAttributes requestTypeAttr = requestTypeAttributesRepository.findOne(context.getRequestTypeAttributesId()); 
			requestTypeAttributesRepository.delete(requestTypeAttr);
		}

		@Override
		public RequestTypeAttributesModel edit(IKeyBuilder<String> keyBuilder, RequestTypeAttributesModel model) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RequestTypeAttributesModel getByKey(IKeyBuilder<String> keyBuilder,
				RequestTypeAttributesContext context) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection<RequestTypeAttributesModel> getCollection(RequestTypeAttributesContext context) {
			// TODO Auto-generated method stub
			return null;
		}
}
