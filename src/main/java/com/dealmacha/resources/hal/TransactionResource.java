package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

@Relation(value = "transaction", collectionRelation = "transaction")
public class TransactionResource extends ResourceWithEmdedded {
    private String transactionId;
    private String usersId;
    private String code;
    private String amount;
    private String paymentType;
    private String transactionCode;
    private String transactionFor;
    private String transactionErrorCode;
    private String transactionMerchantErrorCode;
    private String transactionMerchantMsg;
    private String createdDate;
    private String transactionCount;
    private String orderStatus;
    private String source;
    private String targetUrl;
    private String cashbackAmount;
    private String refundStatus;
    private String merchantId;
    private String userName;

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    public String getCashbackAmount() {
        return cashbackAmount;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * @return the paymentType
     */
    public String getPaymentType() {
        return paymentType;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public String getSource() {
        return source;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getTransactionCount() {
        return transactionCount;
    }

    /**
     * @return the transactionErrorCode
     */
    public String getTransactionErrorCode() {
        return transactionErrorCode;
    }

    /**
     * @return the transactionFor
     */
    public String getTransactionFor() {
        return transactionFor;
    }

    /**
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @return the transactionMerchantErrorCode
     */
    public String getTransactionMerchantErrorCode() {
        return transactionMerchantErrorCode;
    }

    /**
     * @return the transactionMerchantMsg
     */
    public String getTransactionMerchantMsg() {
        return transactionMerchantMsg;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the usersId
     */
    public String getUsersId() {
        return usersId;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(final String amount) {
        this.amount = amount;
    }

    public void setCashbackAmount(final String cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }

    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }

    public void setOrderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @param paymentType
     *            the paymentType to set
     */
    public void setPaymentType(final String paymentType) {
        this.paymentType = paymentType;
    }

    public void setRefundStatus(final String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public void setSource(final String source) {
        this.source = source;
    }

    public void setTargetUrl(final String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setTransactionCount(final String transactionCount) {
        this.transactionCount = transactionCount;
    }

    /**
     * @param transactionErrorCode
     *            the transactionErrorCode to set
     */
    public void setTransactionErrorCode(final String transactionErrorCode) {
        this.transactionErrorCode = transactionErrorCode;
    }

    /**
     * @param transactionFor
     *            the transactionFor to set
     */
    public void setTransactionFor(final String transactionFor) {
        this.transactionFor = transactionFor;
    }

    /**
     * @param transactionId
     *            the transactionId to set
     */
    public void setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @param transactionMerchantErrorCode
     *            the transactionMerchantErrorCode to set
     */
    public void setTransactionMerchantErrorCode(final String transactionMerchantErrorCode) {
        this.transactionMerchantErrorCode = transactionMerchantErrorCode;
    }

    /**
     * @param transactionMerchantMsg
     *            the transactionMerchantMsg to set
     */
    public void setTransactionMerchantMsg(final String transactionMerchantMsg) {
        this.transactionMerchantMsg = transactionMerchantMsg;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * @param usersId
     *            the usersId to set
     */
    public void setUsersId(final String usersId) {
        this.usersId = usersId;
    }

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

}
