package com.dealmacha.dao;

import com.dealmacha.domain.Transaction;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Serializable> {

    /**
     * @param dateId
     * @return
     */
    @Query("select DATE(createdDate) from Transaction tr")
    List<Transaction> findDateTransaction(String dateId);

    /**
     * @param merchantId
     * @return
     */
    @Query("select tr from Transaction tr where tr.merchant.id=?1")
    List<Transaction> findMerchantTransaction(String merchantId);

    /**
     * @param usersId
     * @return
     */
    @Query("select tr from Transaction tr where tr.users.id=?1")
    List<Transaction> findUserTransaction(String usersId);

    /**
     * @return
     */

    @Query("select MAX(transactionCount) from Transaction tr")
    Integer getMaxCode();
    
@Query("select SUM(amount) from Transaction tr")
	Integer getTotalTransaction();

@Query("select SUM(cashbackAmount) from Transaction tr")
Integer getCommissionReceived();

@Query("select tr from Transaction tr where tr.orderStatus='FAIL' Order By tr.createdDate")
List<Transaction> findByAll();


    /*@Query("select SUM(amount) from Transaction tr ")
	List<Transaction> findByAllTotalTransaction();*/

}
