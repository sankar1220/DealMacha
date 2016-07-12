/**
 *
 */
package com.dealmacha.model;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author arthvedi5
 *
 */
@Component("merchantCategoryMargin")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MerchantCategoryMarginModel extends AbstractModel {

    private String merchantCategoryId;
    private String customerType;
    private String channel;
    private String commission;
    private String commissionType;
    private String dealmachaCommission;
    private String dealmachaCommissionType;

    public String getChannel() {
        return channel;
    }

    public String getCommission() {
        return commission;
    }

    public String getCommissionType() {
        return commissionType;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getDealmachaCommission() {
        return dealmachaCommission;
    }

    public String getDealmachaCommissionType() {
        return dealmachaCommissionType;
    }

    public String getMerchantCategoryId() {
        return merchantCategoryId;
    }

    public void setChannel(final String channel) {
        this.channel = channel;
    }

    public void setCommission(final String commission) {
        this.commission = commission;
    }

    public void setCommissionType(final String commissionType) {
        this.commissionType = commissionType;
    }

    public void setCustomerType(final String customerType) {
        this.customerType = customerType;
    }

    public void setDealmachaCommission(final String dealmachaCommission) {
        this.dealmachaCommission = dealmachaCommission;
    }

    public void setDealmachaCommissionType(final String dealmachaCommissionType) {
        this.dealmachaCommissionType = dealmachaCommissionType;
    }

    public void setMerchantCategoryId(final String merchantCategoryId) {
        this.merchantCategoryId = merchantCategoryId;
    }

}
