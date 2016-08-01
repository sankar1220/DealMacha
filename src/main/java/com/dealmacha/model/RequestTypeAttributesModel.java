package com.dealmacha.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

	@Component("requestTypeAttributesModel")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public class RequestTypeAttributesModel extends AbstractModel {
		
		private String requestTypesId;
		private String requestTypeAttribute;
		private String status;
		
		public String getRequestTypesId() {
			return requestTypesId;
		}
		public void setRequestTypesId(String requestTypesId) {
			this.requestTypesId = requestTypesId;
		}
		public String getRequestTypeAttribute() {
			return requestTypeAttribute;
		}
		public void setRequestTypeAttribute(String requestTypeAttribute) {
			this.requestTypeAttribute = requestTypeAttribute;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
		
}
