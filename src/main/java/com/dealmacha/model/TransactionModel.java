package com.dealmacha.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("transactionModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransactionModel extends AbstractModel {
    private String usersId;
    private String code;
    private String amount;
    private String paymentType;
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
    private List<PaymentModel> paymentModels = new ArrayList<PaymentModel>(0);

    public String getAmount() {
        return amount;
    }

    public String getCashbackAmount() {
        return cashbackAmount;
    }

    public String getCode() {
        return code;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public List<PaymentModel> getPaymentModels() {
        return paymentModels;
    }

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

    public String getTransactionErrorCode() {
        return transactionErrorCode;
    }

    public String getTransactionFor() {
        return transactionFor;
    }

    public String getTransactionMerchantErrorCode() {
        return transactionMerchantErrorCode;
    }

    public String getTransactionMerchantMsg() {
        return transactionMerchantMsg;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    public void setCashbackAmount(final String cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }

    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }

    public void setOrderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPaymentModels(final List<PaymentModel> paymentModels) {
        this.paymentModels = paymentModels;
    }

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

    public void setTransactionErrorCode(final String transactionErrorCode) {
        this.transactionErrorCode = transactionErrorCode;
    }

    public void setTransactionFor(final String transactionFor) {
        this.transactionFor = transactionFor;
    }

    public void setTransactionMerchantErrorCode(final String transactionMerchantErrorCode) {
        this.transactionMerchantErrorCode = transactionMerchantErrorCode;
    }

    public void setTransactionMerchantMsg(final String transactionMerchantMsg) {
        this.transactionMerchantMsg = transactionMerchantMsg;
    }

    public void setUsersId(final String usersId) {
        this.usersId = usersId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
