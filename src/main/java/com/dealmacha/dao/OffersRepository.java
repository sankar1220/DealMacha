/**
 *
 */
package com.dealmacha.dao;

import com.dealmacha.domain.Offers;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author arthvedi5
 *
 */
public interface OffersRepository extends PagingAndSortingRepository<Offers, Serializable> {

}
