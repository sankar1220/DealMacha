package com.dealmacha.api.Domain;

import java.util.ArrayList;
import java.util.List;

public class DatayugeUncomparableProduct {

	
	private String product_name;
	private String product_main_category;
	private String product_sub_category;
	private String product_main_store;
	private String product_brand;
	private String product_price;
	private String product_mrp;
	private String product_discount;
	private String product_image_full;
	private String product_image_single;
	private String product_image_thumbnail;
	   private List<DatayugeProductSpecs> productSpecs = new ArrayList<DatayugeProductSpecs>(0);
	    private List<DatayugeProductSpecsAlts> productSpecAlts = new ArrayList<DatayugeProductSpecsAlts>(0);
	    private List<DatayugeProductStores> productStores = new ArrayList<DatayugeProductStores>(0);
		public String getProduct_name() {
			return product_name;
		}
		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}
		public String getProduct_main_category() {
			return product_main_category;
		}
		public void setProduct_main_category(String product_main_category) {
			this.product_main_category = product_main_category;
		}
		public String getProduct_sub_category() {
			return product_sub_category;
		}
		public void setProduct_sub_category(String product_sub_category) {
			this.product_sub_category = product_sub_category;
		}
		public String getProduct_main_store() {
			return product_main_store;
		}
		public void setProduct_main_store(String product_main_store) {
			this.product_main_store = product_main_store;
		}
		public String getProduct_brand() {
			return product_brand;
		}
		public void setProduct_brand(String product_brand) {
			this.product_brand = product_brand;
		}
		public String getProduct_price() {
			return product_price;
		}
		public void setProduct_price(String product_price) {
			this.product_price = product_price;
		}
		public String getProduct_mrp() {
			return product_mrp;
		}
		public void setProduct_mrp(String product_mrp) {
			this.product_mrp = product_mrp;
		}
		public String getProduct_discount() {
			return product_discount;
		}
		public void setProduct_discount(String product_discount) {
			this.product_discount = product_discount;
		}
		public String getProduct_image_full() {
			return product_image_full;
		}
		public void setProduct_image_full(String product_image_full) {
			this.product_image_full = product_image_full;
		}
		public String getProduct_image_single() {
			return product_image_single;
		}
		public void setProduct_image_single(String product_image_single) {
			this.product_image_single = product_image_single;
		}
		public String getProduct_image_thumbnail() {
			return product_image_thumbnail;
		}
		public void setProduct_image_thumbnail(String product_image_thumbnail) {
			this.product_image_thumbnail = product_image_thumbnail;
		}
		public List<DatayugeProductSpecs> getProductSpecs() {
			return productSpecs;
		}
		public void setProductSpecs(List<DatayugeProductSpecs> productSpecs) {
			this.productSpecs = productSpecs;
		}
		public List<DatayugeProductSpecsAlts> getProductSpecAlts() {
			return productSpecAlts;
		}
		public void setProductSpecAlts(List<DatayugeProductSpecsAlts> productSpecAlts) {
			this.productSpecAlts = productSpecAlts;
		}
		public List<DatayugeProductStores> getProductStores() {
			return productStores;
		}
		public void setProductStores(List<DatayugeProductStores> productStores) {
			this.productStores = productStores;
		}

	
	
}
