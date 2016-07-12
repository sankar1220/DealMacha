package com.dealmacha.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("merchantModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MerchantModel extends AbstractModel {
    private String affiliateId;
    private String merchantToken;
    private String merchantName;
    private String merchantImage;
    private String url;
    private String affiliateKey;
    private String description;

    private ArrayList<TransactionModel> transactionModel = new ArrayList<TransactionModel>(0);

    private List<ClickTransactionModel> clickTransactionModels = new ArrayList<ClickTransactionModel>(0);
    private Set<MerchantCategoryModel> merchantCategoryModel = new HashSet<MerchantCategoryModel>(0);

    public String getAffiliateId() {
        return affiliateId;
    }

    public List<ClickTransactionModel> getClickTransactionModels() {
        return clickTransactionModels;
    }

    public String getDescription() {
        return description;
    }

    public Set<MerchantCategoryModel> getMerchantCategoryModel() {
        return merchantCategoryModel;
    }

    public String getMerchantImage() {
        return merchantImage;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMerchantToken() {
        return merchantToken;
    }

    public ArrayList<TransactionModel> getTransactionModel() {
        return transactionModel;
    }

    public String getUrl() {
        return url;
    }

    public void setAffiliateId(final String affiliateId) {
        this.affiliateId = affiliateId;
    }

    public void setClickTransactionModels(final List<ClickTransactionModel> clickTransactionModels) {
        this.clickTransactionModels = clickTransactionModels;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setMerchantCategoryModel(final Set<MerchantCategoryModel> merchantCategoryModel) {
        this.merchantCategoryModel = merchantCategoryModel;
    }

    public void setMerchantImage(final String merchantImage) {
        this.merchantImage = merchantImage;
    }

    public void setMerchantName(final String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMerchantToken(final String merchantToken) {
        this.merchantToken = merchantToken;
    }

    public void setTransactionModel(final ArrayList<TransactionModel> transactionModel) {
        this.transactionModel = transactionModel;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

	public String getAffiliateKey() {
		return affiliateKey;
	}

	public void setAffiliateKey(String affiliateKey) {
		this.affiliateKey = affiliateKey;
	}

}
