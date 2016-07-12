package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

@Relation(value = "clickTransaction", collectionRelation = "clickTransaction")
public class ClickTransactionResource extends ResourceWithEmdedded {
    private String clickTransactionId;
    private String usersId;
    private String merchantId;
    private String historyId;
    private String createdDate;
    private String status;
    private String urlClicked;
    private String clickedSource;
    private String orderStatus;
    private String clickedTarget;
    private String clickedTargetId;

    public String getClickedSource() {
        return clickedSource;
    }

    public String getClickedTarget() {
        return clickedTarget;
    }

    public String getClickedTargetId() {
        return clickedTargetId;
    }

    public String getClickTransactionId() {
        return clickTransactionId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getHistoryId() {
        return historyId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getUrlClicked() {
        return urlClicked;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setClickedSource(final String clickedSource) {
        this.clickedSource = clickedSource;
    }

    public void setClickedTarget(final String clickedTarget) {
        this.clickedTarget = clickedTarget;
    }

    public void setClickedTargetId(final String clickedTargetId) {
        this.clickedTargetId = clickedTargetId;
    }

    public void setClickTransactionId(final String clickTransactionId) {
        this.clickTransactionId = clickTransactionId;
    }

    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }

    public void setHistoryId(final String historyId) {
        this.historyId = historyId;
    }

    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }

    public void setOrderStatus(final String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setUrlClicked(final String urlClicked) {
        this.urlClicked = urlClicked;
    }

    public void setUsersId(final String usersId) {
        this.usersId = usersId;
    }

}
