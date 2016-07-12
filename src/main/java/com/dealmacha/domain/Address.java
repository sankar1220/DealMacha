package com.dealmacha.domain;

// Generated Jul 14, 2014 10:42:38 AM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Address generated by hbm2java
 */
@Entity
@Table(name = "address", catalog = "dealmacha_app")
public class Address extends AbstractDomain implements java.io.Serializable {

    private String line1;
    private String area;
    private String city;
    private String district;
    private String state;
    private String country;
    private String pincode;
    private String type;
    private Set<UserAddress> userAddress = new HashSet<UserAddress>(0);

    public Address() {
    }

    public Address(final String id, final String line1, final String area, final String city, final String district, final String state,
            final String country, final String pincode) {
        this.id = id;
        this.line1 = line1;
        this.city = city;
        this.district = district;
        this.state = state;
        this.country = country;
        this.area = area;
        this.pincode = pincode;
    }

    public Address(final String id, final String line1, final String area, final String city, final String type, final String district,
            final String state, final String country, final String line, final String pincode, final Set<UserAddress> userAddress) {
        this.id = id;
        this.line1 = line1;
        this.area = area;
        this.city = city;
        this.type = type;
        this.district = district;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.userAddress = userAddress;
    }

    @Column(name = "area", length = 45)
    public String getArea() {
        return area;
    }

    @Column(name = "city", nullable = false, length = 45)
    public String getCity() {
        return city;
    }

    @Column(name = "country", nullable = false, length = 45)
    public String getCountry() {
        return country;
    }

    @Column(name = "district", length = 45)
    public String getDistrict() {
        return district;
    }

    @Column(name = "line1", length = 100)
    public String getLine1() {
        return line1;
    }

    @Column(name = "pincode", nullable = false, length = 10)
    public String getPincode() {
        return pincode;
    }

    @Column(name = "state", nullable = false, length = 45)
    public String getState() {
        return state;
    }

    /**
     * @return the type
     */
    @Column(name = "type", length = 45)
    public String getType() {
        return type;
    }

    /* @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
     @JoinTable(name = "user_address", catalog = "dealmacha", joinColumns = { @JoinColumn(name = "address_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "users_id", nullable = false, updatable = false) })
     public Set<Users> getUsers() {
         return users;
     }
    */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    public Set<UserAddress> getUserAddress() {
        return userAddress;
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

    public void setUserAddress(final Set<UserAddress> userAddress) {
        this.userAddress = userAddress;
    }
}