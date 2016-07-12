/**
 *
 */
package com.dealmacha.domain;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * @author arthvedi5
 *
 */
@Entity
@Table(name = "merchant_category_margin", catalog = "dealmacha_app")
public class MerchantCategoryMargin extends AbstractDomain implements java.io.Serializable {
    private MerchantCategory merchantCategory;
    private String customerType;
    private String channel;
    private BigDecimal commission;
    private String commissionType;
    private BigDecimal dealmachaCommission;
    private String dealmachaCommissionType;

    public MerchantCategoryMargin() {

    }

    public MerchantCategoryMargin(final MerchantCategory merchantCategory, final String customerType, final String channel,
            final BigDecimal commission, final String commissionType, final BigDecimal dealmachaCommission,
            final String dealmachaCommissionType) {
        this.merchantCategory = merchantCategory;
        this.channel = channel;
        this.customerType = customerType;
        this.commission = commission;
        setDealmachaCommission(dealmachaCommission);
        setDealmachaCommissionType(dealmachaCommissionType);
        setCommissionType(commissionType);
    }

    @Column(name = "channel", nullable = false, length = 45)
    public String getChannel() {
        return channel;
    }

    @Column(name = "commission", precision = 7)
    public BigDecimal getCommission() {
        return commission;
    }

    @Column(name = "commission_type", nullable = false, length = 45)
    public String getCommissionType() {
        return commissionType;
    }

    @Column(name = "customer_type", nullable = false, length = 45)
    public String getCustomerType() {
        return customerType;
    }

    @Column(name = "dealmacha_commission", precision = 7)
    public BigDecimal getDealmachaCommission() {
        return dealmachaCommission;
    }

    @Column(name = "dealmacha_commission_type", nullable = false, length = 45)
    public String getDealmachaCommissionType() {
        return dealmachaCommissionType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_category_id", nullable = false)
    public MerchantCategory getMerchantCategory() {
        return merchantCategory;
    }

    public void setChannel(final String channel) {
        this.channel = channel;
    }

    public void setCommission(final BigDecimal commission) {
        this.commission = commission;
    }

    public void setCommissionType(final String commissionType) {
        this.commissionType = commissionType;
    }

    public void setCustomerType(final String customerType) {
        this.customerType = customerType;
    }

    public void setDealmachaCommission(final BigDecimal dealmachaCommission) {
        this.dealmachaCommission = dealmachaCommission;
    }

    public void setDealmachaCommissionType(final String dealmachaCommissionType) {
        this.dealmachaCommissionType = dealmachaCommissionType;
    }

    public void setMerchantCategory(final MerchantCategory merchantCategory) {
        this.merchantCategory = merchantCategory;
    }

}
