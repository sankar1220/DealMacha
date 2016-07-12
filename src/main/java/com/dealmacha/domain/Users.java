package com.dealmacha.domain;

// Generated Apr 28, 2016 4:56:47 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "dealmacha_app")
public class Users extends AbstractDomain implements java.io.Serializable {

    private String userName;
    private String age;
    private String emailId;
    private String mobileNo;
    private String alternateMobileNo;
    private String password;
    private String userCode;
    private String status;
    private String emailStatus;
    private String authenticateStatus;
    private Date createdDate;
    private Integer userCount;
    private String userType;
    private Set<Roles> roles = new HashSet<Roles>(0);
    private Set<Account> accounts= new HashSet<Account>(0);
    private Set<UserAddress> userAddresses = new HashSet<UserAddress>(0);
    private Set<Transaction> transactions = new HashSet<Transaction>(0);
    private Set<ClickTransaction> clickTransactions = new HashSet<ClickTransaction>(0);
    private Set<CashbackTransaction> cashbackTransactions = new HashSet<CashbackTransaction>(0);
    private Set<Requests> requests = new HashSet<Requests>(0);
    
    public Users() {
    }

    public Users(final String age, final String userType, final String status, final String emailStatus, final Integer userCount,
            final String userCode, final String mobileNo, final String emailId, final String password, final String authenticateStatus,
            final Date createdDate, final String occupation, final String userName) {

        this.authenticateStatus = authenticateStatus;
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.age = age;
        this.emailId = emailId;
        this.password = password;
        this.status = status;
        this.userType = userType;
        this.emailStatus = emailStatus;
        this.createdDate = createdDate;
        this.userCode = userCode;
        this.userCount = userCount;
    }

    public Users(final String userName, final String userType, final String age, final String status, final String mobileNo,
            final String emailId, final String phoneNo, final String emailStatus, final Integer userCount, final String userCode,
            final String password, final String authenticateStatus, final String alternateMobileNo, final Set<Roles> roles,
            final Set<ClickTransaction> clickTransactions,final Set<Requests> requests,final Set<CashbackTransaction> cashbackTransactions,final Set<Account> accounts, final Set<UserAddress> userAddresses, final Set<Transaction> transactions,
            final Date createdDate) {
this.accounts=accounts;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.password = password;
        this.roles = roles;
        this.setCashbackTransactions(cashbackTransactions);
        this.status = status;
        this.userType = userType;
        this.userAddresses = userAddresses;
        this.authenticateStatus = authenticateStatus;
        this.emailStatus = emailStatus;
        this.age = age;
        this.setRequests(requests);
        this.alternateMobileNo = alternateMobileNo;
        this.transactions = transactions;
        this.createdDate = createdDate;
        this.userName = userName;
        this.userCode = userCode;
        this.userCount = userCount;
        this.clickTransactions = clickTransactions;
    }

    /**
     * Constructor for Users
     *
     * @param user
     */
    public Users(final Users user) {
        id = user.id;
        userName = user.userName;
        userType = user.userType;
        emailId = user.emailId;
        mobileNo = user.mobileNo;
        password = user.password;
        status = user.status;
        userCode = user.userCode;
        userCount = user.userCount;
    }

    @Column(name = "age", nullable = false, length = 45)
    public String getAge() {
        return age;
    }

    /**
     * @return the alternateMobileNo
     */
    @Column(name = "alternate_mobile_no")
    public String getAlternateMobileNo() {
        return alternateMobileNo;
    }

    @Column(name = "authenticate_status", nullable = false, length = 45)
    public String getAuthenticateStatus() {
        return authenticateStatus;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    public Set<ClickTransaction> getClickTransactions() {
        return clickTransactions;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, length = 19)
    public Date getCreatedDate() {
        return createdDate;
    }

    @Column(name = "email_id", nullable = false, length = 45)
    public String getEmailId() {
        return emailId;
    }

    @Column(name = "email_status", nullable = false, length = 45)
    public String getEmailStatus() {
        return emailStatus;
    }

    @Column(name = "mobile_no", nullable = false, length = 45)
    public String getMobileNo() {
        return mobileNo;
    }

    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    public Set<Roles> getRoles() {
        return roles;
    }

    @Column(name = "status", nullable = false, length = 45)
    public String getStatus() {
        return status;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    /* @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
     @JoinTable(name = "user_address", catalog = "dealmacha", joinColumns = { @JoinColumn(name = "users_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "address_id", nullable = false, updatable = false) })
     public Set<UserAddress> getUserAddresses() {
         return userAddresses;
     }*/
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    public Set<UserAddress> getUserAddress() {
        return userAddresses;
    }

    @Column(name = "user_code", nullable = false, length = 45)
    public String getUserCode() {
        return userCode;
    }

    @Column(name = "user_count", nullable = false)
    public Integer getUserCount() {
        return userCount;
    }

    @Column(name = "user_name", nullable = false, length = 45)
    public String getUserName() {
        return userName;
    }

    @Column(name = "user_type", nullable = false)
    public String getUserType() {
        return userType;
    }

    public void setAge(final String age) {
        this.age = age;
    }

    /**
     * @param alternateMobileNo
     *            the alternateMobileNo to set
     */
    public void setAlternateMobileNo(final String alternateMobileNo) {
        this.alternateMobileNo = alternateMobileNo;
    }

    public void setAuthenticateStatus(final String authenticateStatus) {
        this.authenticateStatus = authenticateStatus;
    }

    public void setClickTransactions(final Set<ClickTransaction> clickTransactions) {
        this.clickTransactions = clickTransactions;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }

    public void setEmailStatus(final String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public void setMobileNo(final String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setRoles(final Set<Roles> roles) {
        this.roles = roles;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setTransactions(final Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setUserAddress(final Set<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public void setUserCode(final String userCode) {
        this.userCode = userCode;
    }

    public void setUserCount(final int userCount) {
        this.userCount = userCount;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setUserType(final String userType) {
        this.userType = userType;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<CashbackTransaction> getCashbackTransactions() {
		return cashbackTransactions;
	}

	public void setCashbackTransactions(Set<CashbackTransaction> cashbackTransactions) {
		this.cashbackTransactions = cashbackTransactions;
	}
	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Requests> getRequests() {
		return requests;
	}

	public void setRequests(Set<Requests> requests) {
		this.requests = requests;
	}
	
}