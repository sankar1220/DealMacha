package com.dealmacha.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("paymentModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PaymentModel extends AbstractModel {
    private String transactionId;
    private String cardNo;
    private String cardType;
    private String cvv;
    private String expireDate;
    private String netBankingId;
    private String nameOnCard;
    private String walletId;
    private String status;

    public String getCardNo() {
        return cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getNetBankingId() {
        return netBankingId;
    }

    public String getStatus() {
        return status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setCardNo(final String cardNo) {
        this.cardNo = cardNo;
    }

    public void setCardType(final String cardType) {
        this.cardType = cardType;
    }

    public void setCvv(final String cvv) {
        this.cvv = cvv;
    }

    public void setExpireDate(final String expireDate) {
        this.expireDate = expireDate;
    }

    public void setNameOnCard(final String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public void setNetBankingId(final String netBankingId) {
        this.netBankingId = netBankingId;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
    }

    public void setWalletId(final String walletId) {
        this.walletId = walletId;
    }

}
