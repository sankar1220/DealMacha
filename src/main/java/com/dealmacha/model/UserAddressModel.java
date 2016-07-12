package com.dealmacha.model;

import org.springframework.stereotype.Component;

@Component("userAddressModel")
public class UserAddressModel extends AbstractModel {
    private String usersId;
    private String addressId;
    private String line1;
    private String area;
    private String city;
    private String district;
    private String state;
    private String country;
    private String type;
    private String pincode;

    /* private String status;
     private Date createdDate;
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

    /* public Date getCreatedDate() {
         return createdDate;
     }
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

    /* public String getStatus() {
         return status;
     }*/

    public String getType() {
        return type;
    }

    public String getUsersId() {
        return usersId;
    }

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

    /* public void setCreatedDate(final Date createdDate) {
         this.createdDate = createdDate;
     }*/

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

    /* public void setStatus(final String status) {
         this.status = status;
     }
    */
    public void setType(final String type) {
        this.type = type;
    }

    public void setUsersId(final String usersId) {
        this.usersId = usersId;
    }

}
