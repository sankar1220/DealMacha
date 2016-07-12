/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.api.Domain.PayoomOffer;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author mohan
 *
 */
@Component
public interface IPayoomOfferService {

    PayoomOffer create(PayoomOffer payoomOffer);

    void deletePayoomOffer(String payoomOfferId);

    List<PayoomOffer> getAll();

    /**
     * @param string
     * @return
     */
    PayoomOffer getPayoomOffer(String payoomOfferId);

    PayoomOffer updatePayoomOffer(PayoomOffer payoomOffer);

}
