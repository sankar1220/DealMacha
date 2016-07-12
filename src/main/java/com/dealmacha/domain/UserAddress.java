package com.dealmacha.domain;

// Generated Oct 29, 2014 10:34:11 PM by Hibernate Tools 3.6.0

import javax.persistence.*;

/**
 * UserAddress generated by hbm2java
 */
@Entity
@Table(name = "user_address", catalog = "dealmacha_app")
public class UserAddress extends AbstractDomain implements java.io.Serializable {

    private Users users;
    private Address address;

    /*private String status;
    private Date createdDate;*/

    public UserAddress() {
    }

    public UserAddress(final Users users, final Address address/*, final String status, final Date createdDate*/) {

        this.users = users;
        this.address = address;
        /* this.status = status;
         this.createdDate = createdDate;*/
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    public Address getAddress() {
        return address;
    }

    /*  @Temporal(TemporalType.TIMESTAMP)
      @Column(name = "created_date", nullable = false, length = 19)
      public Date getCreatedDate() {
          return createdDate;
      }

      @Column(name = "status")
      public String getStatus() {
          return status;
      }*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    public Users getUsers() {
        return users;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    /* public void setCreatedDate(final Date createdDate) {
         this.createdDate = createdDate;
     }

     public void setStatus(final String status) {
         this.status = status;
     }*/

    public void setUsers(final Users users) {
        this.users = users;
    }

}
