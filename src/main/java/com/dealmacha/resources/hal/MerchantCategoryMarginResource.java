/**
 *
 */
package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

/**
 * @author arthvedi5
 *
 */
@Relation(value = "merchantCategoryMargin", collectionRelation = "merchantCategoryMargin")
public class MerchantCategoryMarginResource extends ResourceWithEmdedded {
    private String merchantCategoryMarginId;
    private String merchantCategoryId;
    private String customerType;
    private String channel;
    
    private String commission;
    private String commissionType;
    private String dealmachaCommission;
    private String dealmachaCommissionType;
	public String getMerchantCategoryMarginId() {
		return merchantCategoryMarginId;
	}
	public void setMerchantCategoryMarginId(String merchantCategoryMarginId) {
		this.merchantCategoryMarginId = merchantCategoryMarginId;
	}
	public String getMerchantCategoryId() {
		return merchantCategoryId;
	}
	public void setMerchantCategoryId(String merchantCategoryId) {
		this.merchantCategoryId = merchantCategoryId;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getCommissionType() {
		return commissionType;
	}
	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}
	public String getDealmachaCommission() {
		return dealmachaCommission;
	}
	public void setDealmachaCommission(String dealmachaCommission) {
		this.dealmachaCommission = dealmachaCommission;
	}
	public String getDealmachaCommissionType() {
		return dealmachaCommissionType;
	}
	public void setDealmachaCommissionType(String dealmachaCommissionType) {
		this.dealmachaCommissionType = dealmachaCommissionType;
	}

}
