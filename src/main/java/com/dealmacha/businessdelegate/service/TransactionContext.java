package com.dealmacha.businessdelegate.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionContext implements IBusinessDelegateContext {
    private String all;
    private String transactionId;
    private String usersId;
    private String merchantId;
    private String dateId;
    private String allTotalTransaction;
    private String failTransactions;
    

    public String getAll() {
        return all;
    }

    /**
     * @return the date
     */
    public String getDateId() {
        return dateId;
    }

    /**
     * @return the merchantId
     */
    public String getMerchantId() {
        return merchantId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @return the usersId
     */
    public String getUsersId() {
        return usersId;
    }

    public void setAll(final String all) {
        this.all = all;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDateId(final String dateId) {
        this.dateId = dateId;
    }

    /**
     * @param merchantId
     *            the merchantId to set
     */
    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }

    public void setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @param usersId
     *            the usersId to set
     */
    public void setUsersId(final String usersId) {
        this.usersId = usersId;
    }

	public String getAllTotalTransaction() {
		return allTotalTransaction;
	}

	public void setAllTotalTransaction(String allTotalTransaction) {
		this.allTotalTransaction = allTotalTransaction;
	}

	public String getFailTransactions() {
		return failTransactions;
	}

	public void setFailTransactions(String failTransactions) {
		this.failTransactions = failTransactions;
	}

	


}
