package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

@Relation(value = "payment", collectionRelation = "payment")
public class PaymentResource extends ResourceWithEmdedded {
    private String paymentId;
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

    public String getPaymentId() {
        return paymentId;
    }

    public String getStatus() {
        return status;
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

    public void setPaymentId(final String paymentId) {
        this.paymentId = paymentId;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setWalletId(final String walletId) {
        this.walletId = walletId;
    }

}
