/**
 *
 */
package com.dealmacha.api.Domain;

/**
 * @author Jay
 *
 */
public class DatayugeProductStores {
    private String product_store;
    private String product_store_logo;
    private String product_price;
    private String product_offers;
    private String product_price_after;
    private String product_store_url;
    private String product_color;
    private String product_delivery;
    private String product_delivery_cost;
    private String product_isemi;
    private String product_iscod;
    private String product_return_time;

    public String getProduct_color() {
        return product_color;
    }

    public String getProduct_delivery() {
        return product_delivery;
    }

    public String getProduct_delivery_cost() {
        return product_delivery_cost;
    }

    public String getProduct_iscod() {
        return product_iscod;
    }

    public String getProduct_isemi() {
        return product_isemi;
    }

    public String getProduct_offers() {
        return product_offers;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_price_after() {
        return product_price_after;
    }

    /**
     * @return the product_return_time
     */
    public String getProduct_return_time() {
        return product_return_time;
    }

    public String getProduct_store() {
        return product_store;
    }

    public String getProduct_store_logo() {
        return product_store_logo;
    }

    public String getProduct_store_url() {
        return product_store_url;
    }

    public void setProduct_color(final String product_color) {
        this.product_color = product_color;
    }

    public void setProduct_delivery(final String product_delivery) {
        this.product_delivery = product_delivery;
    }

    public void setProduct_delivery_cost(final String product_delivery_cost) {
        this.product_delivery_cost = product_delivery_cost;
    }

    public void setProduct_iscod(final String product_iscod) {
        this.product_iscod = product_iscod;
    }

    public void setProduct_isemi(final String product_isemi) {
        this.product_isemi = product_isemi;
    }

    public void setProduct_offers(final String product_offers) {
        this.product_offers = product_offers;
    }

    public void setProduct_price(final String product_price) {
        this.product_price = product_price;
    }

    public void setProduct_price_after(final String product_price_after) {
        this.product_price_after = product_price_after;
    }

    /**
     * @param product_return_time
     *            the product_return_time to set
     */
    public void setProduct_return_time(final String product_return_time) {
        this.product_return_time = product_return_time;
    }

    public void setProduct_store(final String product_store) {
        this.product_store = product_store;
    }

    public void setProduct_store_logo(final String product_store_logo) {
        this.product_store_logo = product_store_logo;
    }

    public void setProduct_store_url(final String product_store_url) {
        this.product_store_url = product_store_url;
    }

}
