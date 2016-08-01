/**
 *
 */
package com.dealmacha.api.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jay
 *
 */
public class DatayugeProduct {
    private String product_name;
    private String product_model;
    private String product_brand;
    private String product_rating;
    private String product_main_category;
    private String product_sub_category;
    private String isavailable;
    private String[] available_colors;
    private String[] product_image_full;
    private String[] product_image_single;
    private String[] product_image_thumbnail;
    private List<DatayugeProductSpecs> productSpecs = new ArrayList<DatayugeProductSpecs>(0);
    private List<DatayugeProductSpecsAlts> productSpecAlts = new ArrayList<DatayugeProductSpecsAlts>(0);
    private List<DatayugeProductStores> productStores = new ArrayList<DatayugeProductStores>(0);

    
    
    public String getProduct_brand() {
		return product_brand;
	}

	public void setProduct_brand(String product_brand) {
		this.product_brand = product_brand;
	}

	public String getProduct_rating() {
		return product_rating;
	}

	public void setProduct_rating(String product_rating) {
		this.product_rating = product_rating;
	}

	public String[] getAvailable_colors() {
		return available_colors;
	}

	public void setAvailable_colors(String[] available_colors) {
		this.available_colors = available_colors;
	}

	

    public String[] getProduct_image_full() {
        return product_image_full;
    }

    public String[] getProduct_image_single() {
        return product_image_single;
    }

    public String[] getProduct_image_thumbnail() {
        return product_image_thumbnail;
    }

    public String getProduct_main_category() {
        return product_main_category;
    }

    public String getProduct_model() {
        return product_model;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_sub_category() {
        return product_sub_category;
    }

    /**
     * @return the productSpecAlts
     */
    public List<DatayugeProductSpecsAlts> getProductSpecAlts() {
        return productSpecAlts;
    }

    /**
     * @return the productSpecs
     */
    public List<DatayugeProductSpecs> getProductSpecs() {
        return productSpecs;
    }

    /**
     * @return the productStores
     */
    public List<DatayugeProductStores> getProductStores() {
        return productStores;
    }

 

    public void setProduct_image_full(final String[] product_image_full) {
        this.product_image_full = product_image_full;
    }

    public void setProduct_image_single(final String[] product_image_single) {
        this.product_image_single = product_image_single;
    }

    public void setProduct_image_thumbnail(final String[] product_image_thumbnail) {
        this.product_image_thumbnail = product_image_thumbnail;
    }

    public void setProduct_main_category(final String product_main_category) {
        this.product_main_category = product_main_category;
    }

    public void setProduct_model(final String product_model) {
        this.product_model = product_model;
    }

    public void setProduct_name(final String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_sub_category(final String product_sub_category) {
        this.product_sub_category = product_sub_category;
    }

    /**
     * @param productSpecAlts
     *            the productSpecAlts to set
     */
    public void setProductSpecAlts(final List<DatayugeProductSpecsAlts> productSpecAlts) {
        this.productSpecAlts = productSpecAlts;
    }

    /**
     * @param productSpecs
     *            the productSpecs to set
     */
    public void setProductSpecs(final List<DatayugeProductSpecs> productSpecs) {
        this.productSpecs = productSpecs;
    }

    /**
     * @param productStores
     *            the productStores to set
     */
    public void setProductStores(final List<DatayugeProductStores> productStores) {
        this.productStores = productStores;
    }

	public String getIsavailable() {
		return isavailable;
	}

	public void setIsavailable(String isavailable) {
		this.isavailable = isavailable;
	}
}
