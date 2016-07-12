package com.dealmacha.api.Domain;

public class FlipKartOrder {

    private String price;
    private String category;
    private String title;
    private String productId;
    private String quantity;
    private String sales;
    private String status;
    private String affiliateOrderItemId;
    private String orderDate;
    private String commissionRate;
    private String tentativeCommission;
    private String affExtParam1;
    private String salesChannel;
    private String customerType;
    private String salesAmount;
    private String salesCurrency;
    private String tentativeCommissionAmount;
    private String tentativeCommissionCurrency;

    public String getAffExtParam1() {
        return affExtParam1;
    }

    public String getAffiliateOrderItemId() {
        return affiliateOrderItemId;
    }

    public String getCategory() {
        return category;
    }

    public String getCommissionRate() {
        return commissionRate;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getPrice() {
        return price;
    }

    public String getProductId() {
        return productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getSales() {
        return sales;
    }

    /**
     * @return the salesAmount
     */
    public String getSalesAmount() {
        return salesAmount;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    /**
     * @return the salesCurrency
     */
    public String getSalesCurrency() {
        return salesCurrency;
    }

    public String getStatus() {
        return status;
    }

    public String getTentativeCommission() {
        return tentativeCommission;
    }

    /**
     * @return the tentativeCommissionAmount
     */
    public String getTentativeCommissionAmount() {
        return tentativeCommissionAmount;
    }

    /**
     * @return the tentativeCommissionCurrency
     */
    public String getTentativeCommissionCurrency() {
        return tentativeCommissionCurrency;
    }

    public String getTitle() {
        return title;
    }

    public void setAffExtParam1(final String affExtParam1) {
        this.affExtParam1 = affExtParam1;
    }

    public void setAffiliateOrderItemId(final String affiliateOrderItemId) {
        this.affiliateOrderItemId = affiliateOrderItemId;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public void setCommissionRate(final String commissionRate) {
        this.commissionRate = commissionRate;
    }

    public void setCustomerType(final String customerType) {
        this.customerType = customerType;
    }

    public void setOrderDate(final String orderDate) {
        this.orderDate = orderDate;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public void setProductId(final String productId) {
        this.productId = productId;
    }

    public void setQuantity(final String quantity) {
        this.quantity = quantity;
    }

    public void setSales(final String sales) {
        this.sales = sales;
    }

    /**
     * @param salesAmount
     *            the salesAmount to set
     */
    public void setSalesAmount(final String salesAmount) {
        this.salesAmount = salesAmount;
    }

    public void setSalesChannel(final String salesChannel) {
        this.salesChannel = salesChannel;
    }

    /**
     * @param salesCurrency
     *            the salesCurrency to set
     */
    public void setSalesCurrency(final String salesCurrency) {
        this.salesCurrency = salesCurrency;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setTentativeCommission(final String tentativeCommission) {
        this.tentativeCommission = tentativeCommission;
    }

    /**
     * @param tentativeCommissionAmount
     *            the tentativeCommissionAmount to set
     */
    public void setTentativeCommissionAmount(final String tentativeCommissionAmount) {
        this.tentativeCommissionAmount = tentativeCommissionAmount;
    }

    /**
     * @param tentativeCommissionCurrency
     *            the tentativeCommissionCurrency to set
     */
    public void setTentativeCommissionCurrency(final String tentativeCommissionCurrency) {
        this.tentativeCommissionCurrency = tentativeCommissionCurrency;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

}
