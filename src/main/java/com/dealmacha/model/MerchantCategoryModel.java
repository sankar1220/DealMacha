/**
 *
 */
package com.dealmacha.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author arthvedi5
 *
 */
@Component("merchantCategoryModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MerchantCategoryModel extends AbstractModel {

    private String merchantId;
    private String categoryName;
    private String merchantCategoryCode;
    private String merchantCategoryCount;

    private List<MerchantCategoryMarginModel> merchantCategoryMarginModel = new ArrayList<MerchantCategoryMarginModel>(0);

    public String getCategoryName() {
        return categoryName;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public String getMerchantCategoryCount() {
        return merchantCategoryCount;
    }

    public List<MerchantCategoryMarginModel> getMerchantCategoryMarginModel() {
        return merchantCategoryMarginModel;
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

    public void setMerchantCategoryMarginModel(final List<MerchantCategoryMarginModel> merchantCategoryMarginModel) {
        this.merchantCategoryMarginModel = merchantCategoryMarginModel;
    }

    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
    }

}
