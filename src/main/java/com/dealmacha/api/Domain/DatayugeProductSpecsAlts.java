/**
 *
 */
package com.dealmacha.api.Domain;

/**
 * @author Jay
 *
 */
public class DatayugeProductSpecsAlts {
    private String product_spec_name;
    private String product_spec_value;
    private String product_spec_category;

    public String getProduct_spec_name() {
        return product_spec_name;
    }

    public String getProduct_spec_value() {
        return product_spec_value;
    }

    public void setProduct_spec_name(final String product_spec_name) {
        this.product_spec_name = product_spec_name;
    }

    public void setProduct_spec_value(final String product_spec_value) {
        this.product_spec_value = product_spec_value;
    }

	public String getProduct_spec_category() {
		return product_spec_category;
	}

	public void setProduct_spec_category(String product_spec_category) {
		this.product_spec_category = product_spec_category;
	}
}
