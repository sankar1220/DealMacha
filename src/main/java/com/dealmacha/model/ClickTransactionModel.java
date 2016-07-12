package com.dealmacha.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("clickTransactionModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClickTransactionModel extends AbstractModel {
    private String usersId;
    private String merchantId;

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

    public String getCreatedDate() {
        return createdDate;
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

    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
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
