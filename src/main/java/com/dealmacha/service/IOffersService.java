/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.domain.Offers;

import java.util.List;

/**
 * @author arthvedi5
 *
 */
public interface IOffersService {
    Offers create(Offers offers);

    void deleteOffers(String offersId);

    List<Offers> getAll();

    /**
     * @param string
     * @return
     */
    Offers getOffers(String offersId);

    Offers updateOffers(Offers offers);
}
