/**
 *
 */
package com.dealmacha.dao;

import com.dealmacha.domain.MailConfig;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author arthvedi
 *
 */

public interface MailConfigRepository extends PagingAndSortingRepository<MailConfig, Serializable> {

    /**
     * @param status
     * @param attributeValue
     * @param attributeName
     * @return
     */
    @Query("select distinct mc from MailConfig mc where mc.status=?1 and mc.mailAttributeValue=?2 and mc.mailAttributeName=?3")
    MailConfig findUserActivationMailConfig(String status, String attributeValue, String attributeName);

    /**
     * @param status
     * @param attributeValue
     * @param attributeName
     * @return
     */
    @Query("select distinct mc from MailConfig mc where mc.status=?1 and mc.mailAttributeValue=?2 and mc.mailAttributeName=?3")
    MailConfig findUserlacedOrderMailConfig(String status, String attributeValue, String attributeName);

    /**
     * @param status
     * @param attributeValue
     * @param attributeName
     * @return
     */
    @Query("select distinct mc from MailConfig mc where mc.status=?1 and mc.mailAttributeValue=?2 and mc.mailAttributeName=?3")
    MailConfig findUserRegistrationMailConfig(String status, String attributeValue, String attributeName);

}
