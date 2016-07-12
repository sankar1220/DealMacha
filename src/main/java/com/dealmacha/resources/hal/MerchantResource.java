package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

@Relation(value = "merchant", collectionRelation = "merchant")
public class MerchantResource extends ResourceWithEmdedded {

    private String merchantId;
    private String affliateId;
    private String description;
    private String merchantToken;
    private String url;
    private String affiliateKey;
    
    public String getAffiliateKey() {
		return affiliateKey;
	}

	public void setAffiliateKey(String affiliateKey) {
		this.affiliateKey = affiliateKey;
	}

	private String merchantName;

    private String merchantImage;

    public String getAffliateId() {
        return affliateId;
    }

    public String getDescription() {
        return description;
    }

    public String getMerchantId() {
        return merchantId;
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

    public String getUrl() {
        return url;
    }

    public void setAffliateId(final String affliateId) {
        this.affliateId = affliateId;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setMerchantId(final String merchantId) {
        this.merchantId = merchantId;
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

    public void setUrl(final String url) {
        this.url = url;
    }

}
