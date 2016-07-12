/**
 *
 */
package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

/**
 * @author arthvedi5
 *
 */
@Relation(value = "merchantCategory", collectionRelation = "merchantCategory")
public class MerchantCategoryResource extends ResourceWithEmdedded {
    private String merchantCategoryId;
    private String merchantId;
    private String categoryName;
    private String merchantCategoryCode;
    private String merchantCategoryCount;

    public String getCategoryName() {
        return categoryName;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public String getMerchantCategoryCount() {
        return merchantCategoryCount;
    }

    public String getMerchantCategoryId() {
        return merchantCategoryId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

    public void setMerchantCategoryCode(final String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public void setMerchantCategoryCount(final String merchantCategoryCount) {
        this.merchantCategoryCount = merchantCategoryCount;
    }

    public void setMerchantCategoryId(final String merchantCategoryId) {
        this.merchantCategoryId = merchantCategoryId;
    }

    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }

}
