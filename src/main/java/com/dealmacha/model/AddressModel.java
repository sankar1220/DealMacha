package com.dealmacha.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author mohan
 */
@Component("addressModel")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AddressModel extends AbstractModel {

    private String line1;
    private String area;
    private String city;
    private String district;
    private String state;
    private String country;
    private String type;
    private String pincode;
    private List<UserAddressModel> userAddressModels = new ArrayList<UserAddressModel>(0);

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    /**
     * @return the district
     */
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

    public List<UserAddressModel> getUserAddressModels() {
        return userAddressModels;
    }

    /**
     * @return the usersModels
     */

    public void setArea(final String area) {
        this.area = area;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * @param district
     *            the district to set
     */
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

    public void setUserAddressModels(final List<UserAddressModel> userAddressModels) {
        this.userAddressModels = userAddressModels;
    }

    /*public String getFirmBranchId() {
    	return firmBranchId;
    }

    public void setFirmBranchId(String firmBranchId) {
    	this.firmBranchId = firmBranchId;
    }*/

}
