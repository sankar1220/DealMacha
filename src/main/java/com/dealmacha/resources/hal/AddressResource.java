package com.dealmacha.resources.hal;

import org.springframework.hateoas.core.Relation;

@Relation(value = "address", collectionRelation = "address")
public class AddressResource extends ResourceWithEmdedded {

    private String addressId;
    private String line1;
    private String area;
    private String city;
    private String district;
    private String state;
    private String country;
    private String pincode;
    private String type;

    /**
     * @return the addressId
     */
    public String getAddressId() {
        return addressId;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }

    public String getLine1() {
        return line1;
    }

    public String getPincode() {
        return pincode;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    /**
     * @param addressId
     *            the addressId to set
     */
    public void setAddressId(final String addressId) {
        this.addressId = addressId;
    }

    public void setArea(final String area) {
        this.area = area;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public void setDistrict(final String district) {
        this.district = district;
    }

    public void setLine1(final String line1) {
        this.line1 = line1;
    }

    public void setPincode(final String pincode) {
        this.pincode = pincode;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public void setType(final String type) {
        this.type = type;
    }

}
