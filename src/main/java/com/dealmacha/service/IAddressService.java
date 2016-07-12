package com.dealmacha.service;

import com.dealmacha.domain.Address;

import java.util.List;

/**
 * @author mohan
 *
 */

public interface IAddressService {

    Address create(Address address);

    void deleteAddress(String addressId);

    /**
     * @param string
     * @return
     */
    Address getAddress(String addressId);

    List<Address> getAll();

    List<Address> getCity(String city);

    /**
     * @param city
     * @return
     */
    /*  List<Address> getCity(String city);

      Address getEntity(String entityId);

     *//**
     * @param state
     * @return
     */
    /*
    List<Address> getState(String state);*/

    Address updateAddress(Address address);

}
