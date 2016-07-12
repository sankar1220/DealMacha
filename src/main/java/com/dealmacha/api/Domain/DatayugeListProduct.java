package com.dealmacha.api.Domain;

public class DatayugeListProduct {
    private String product_title;
    private String product_lowest_price;
    private String product_link;
    private String product_category;
    private String product_sub_cate;
    private String product_image;
    private String can_compare;

    public String getCan_compare() {
        return can_compare;
    }

    public String getProduct_category() {
        return product_category;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getProduct_link() {
        return product_link;
    }

    public String getProduct_lowest_price() {
        return product_lowest_price;
    }

    public String getProduct_sub_cate() {
        return product_sub_cate;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setCan_compare(final String can_compare) {
        this.can_compare = can_compare;
    }

    public void setProduct_category(final String product_category) {
        this.product_category = product_category;
    }

    public void setProduct_image(final String product_image) {
        this.product_image = product_image;
    }

    public void setProduct_link(final String product_link) {
        this.product_link = product_link;
    }

    public void setProduct_lowest_price(final String product_lowest_price) {
        this.product_lowest_price = product_lowest_price;
    }

    public void setProduct_sub_cate(final String product_sub_cate) {
        this.product_sub_cate = product_sub_cate;
    }

    public void setProduct_title(final String product_title) {
        this.product_title = product_title;
    }
}
