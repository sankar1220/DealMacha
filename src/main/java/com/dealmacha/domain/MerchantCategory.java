/**
 *
 */
package com.dealmacha.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * @author arthvedi5
 *
 */
@Entity
@Table(name = "merchant_category", catalog = "dealmacha_app")
public class MerchantCategory extends AbstractDomain implements java.io.Serializable {

    private Merchant merchant;
    private String categoryName;
    private String merchantCategoryCode;
    private Integer merchantCategoryCount;
    private Set<MerchantCategoryMargin> merchantCategoryMargin = new HashSet<MerchantCategoryMargin>(0);

    public MerchantCategory() {
    }

    public MerchantCategory(final Merchant merchant, final String categoryName, final String merchantCategoryCode) {

        this.merchant = merchant;
        this.categoryName = categoryName;
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public MerchantCategory(final Merchant merchant, final String categoryName, final String merchantCategoryCode,
            final Set<MerchantCategoryMargin> merchantCategoryMargin) {

        this.merchant = merchant;
        this.categoryName = categoryName;
        this.merchantCategoryCode = merchantCategoryCode;
        setMerchantCategoryMargin(merchantCategoryMargin);
    }

    @Column(name = "category_name", nullable = false, length = 45)
    public String getCategoryName() {
        return categoryName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id", nullable = false)
    public Merchant getMerchant() {
        return merchant;
    }

    @Column(name = "merchant_category_code", nullable = false, length = 45)
    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    @Column(name = "merchant_category_count", nullable = false)
    public Integer getMerchantCategoryCount() {
        return merchantCategoryCount;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "merchantCategory")
    public Set<MerchantCategoryMargin> getMerchantCategoryMargin() {
        return merchantCategoryMargin;
    }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

    public void setMerchant(final Merchant merchant) {
        this.merchant = merchant;
    }

    public void setMerchantCategoryCode(final String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public void setMerchantCategoryCount(final Integer merchantCategoryCount) {
        this.merchantCategoryCount = merchantCategoryCount;
    }

    public void setMerchantCategoryMargin(final Set<MerchantCategoryMargin> merchantCategoryMargin) {
        this.merchantCategoryMargin = merchantCategoryMargin;
    }

}
