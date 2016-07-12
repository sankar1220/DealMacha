package com.dealmacha.resources.hal;

import java.util.Date;

import org.springframework.hateoas.core.Relation;

@Relation(value="mailConfig",collectionRelation="mailConfig")
public class MailConfigResource extends ResourceWithEmdedded{
	private String mailConfigId;
	 private String mailAttributeName;
	    private String mailAttributeValue;
	    private String status;
	    private String createdDate;
	    private String modifiedDate;
		public String getMailConfigId() {
			return mailConfigId;
		}
		public void setMailConfigId(String mailConfigId) {
			this.mailConfigId = mailConfigId;
		}
		public String getMailAttributeName() {
			return mailAttributeName;
		}
		public void setMailAttributeName(String mailAttributeName) {
			this.mailAttributeName = mailAttributeName;
		}
		public String getMailAttributeValue() {
			return mailAttributeValue;
		}
		public void setMailAttributeValue(String mailAttributeValue) {
			this.mailAttributeValue = mailAttributeValue;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
		}
		public String getModifiedDate() {
			return modifiedDate;
		}
		public void setModifiedDate(String modifiedDate) {
			this.modifiedDate = modifiedDate;
		}
	    

}
