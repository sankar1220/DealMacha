package com.dealmacha.domain;

// Generated Apr 28, 2016 4:56:47 PM by Hibernate Tools 4.0.0

import javax.persistence.*;

/**
 * Payment generated by hbm2java
 */
@Entity
@Table(name = "payment", catalog = "dealmacha_app")
public class Payment extends AbstractDomain implements java.io.Serializable {

    private Transaction transaction;
    private String cardNo;
    private String cardType;
    private String cvv;
    private String expireDate;
    private String netBankingId;
    private String nameOnCard;
    private String walletId;
    private String status;

    public Payment() {
    }

    public Payment(final String status, final Transaction transaction, final String cardNo, final String cardType, final String cvv,
            final String expireDate, final String netBankingId, final String nameOnCard, final String walletId) {

        this.status = status;
        this.cardNo = cardNo;
        this.transaction = transaction;
        this.cardType = cardType;
        this.cvv = cvv;
        this.expireDate = expireDate;
        this.netBankingId = netBankingId;
        this.nameOnCard = nameOnCard;
        this.walletId = walletId;
    }

    public Payment(final Transaction transaction, final String status) {

        this.transaction = transaction;
        this.status = status;
    }

    @Column(name = "card_no", length = 45)
    public String getCardNo() {
        return cardNo;
    }

    @Column(name = "card_type", length = 45)
    public String getCardType() {
        return cardType;
    }

    @Column(name = "cvv", length = 45)
    public String getCvv() {
        return cvv;
    }

    @Column(name = "expire_date", length = 45)
    public String getExpireDate() {
        return expireDate;
    }

    @Column(name = "name_on_card", length = 45)
    public String getNameOnCard() {
        return nameOnCard;
    }

    @Column(name = "net_banking_id", length = 45)
    public String getNetBankingId() {
        return netBankingId;
    }

    /**
     * @return the status
     */
    @Column(name = "status", nullable = false)
    public String getStatus() {
        return status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    public Transaction getTransaction() {
        return transaction;
    }

    @Column(name = "wallet_id", length = 100)
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

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    public void setTransaction(final Transaction transaction) {
        this.transaction = transaction;
    }

    public void setWalletId(final String walletId) {
        this.walletId = walletId;
    }

}
