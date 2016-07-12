package com.dealmacha.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "requests", catalog = "dealmacha_app")
public class Requests extends AbstractDomain implements java.io.Serializable {
	private Users users;
	private String requestType;
	private Date createdDate;
	private String requestDescription;
	private String requestStatus;
	private Set<RequestComments> requestComments = new HashSet<RequestComments>(0);
	public Requests() {
    }
 public Requests(final Users users,final String requestType,final  Date createdDate,final String requestDescription,
		 final String requestStatus) {
	 this.users=users;
	 this.requestType=requestType;
	 this.createdDate=createdDate;
	 this.requestDescription=requestDescription;
	 this.requestStatus=requestStatus;	 
    }
public Requests(final Users users,final String requestType,final  Date createdDate,final String requestDescription,
		 final String requestStatus,final Set<RequestComments> requestComments) {
	 this.requestComments=requestComments;
	 this.users=users;
	 this.requestType=requestType;
	 this.createdDate=createdDate;
	 this.requestDescription=requestDescription;
	 this.requestStatus=requestStatus;	 
    }
 
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "users_id", nullable = false)
public Users getUsers() {
	return users;
}
public void setUsers(final Users users) {
	this.users = users;
}
@Column(name = "request_type", nullable = false, length = 45)
public String getRequestType() {
	return requestType;
}
public void setRequestType(String requestType) {
	this.requestType = requestType;
}
@Column(name = "created_date", nullable = false, length = 19)
public Date getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
@Column(name = "request_description",  length = 45)
public String getRequestDescription() {
	return requestDescription;
}
public void setRequestDescription(String requestDescription) {
	this.requestDescription = requestDescription;
}
@Column(name = "request_status", nullable = false, length = 45)
public String getRequestStatus() {
	return requestStatus;
}
public void setRequestStatus(String requestStatus) {
	this.requestStatus = requestStatus;
}

@OneToMany(fetch = FetchType.LAZY, mappedBy = "requests")
public Set<RequestComments> getRequestComments() {
	return requestComments;
}
public void setRequestComments(Set<RequestComments> requestComments) {
	this.requestComments = requestComments;
}



}
