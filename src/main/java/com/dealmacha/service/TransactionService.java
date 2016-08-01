package com.dealmacha.service;

import com.dealmacha.api.keys.UrlKeys;
import com.dealmacha.dao.TransactionRepository;
import com.dealmacha.domain.Merchant;
import com.dealmacha.domain.Transaction;
import com.dealmacha.domain.Users;
import com.dealmacha.util.UniqueKeyGenerator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionService implements ITransactionService, UrlKeys {

    private static final Logger LOGGER = Logger.getLogger(TransactionService.class);
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UniqueKeyGenerator uniqueKeyGenerator;
    @Override
    public Transaction create(final Transaction transaction) {
        Transaction trans = new Transaction();
        Merchant merchant = transaction.getMerchant();
        String dbmerchantName = merchant.getMerchantName().toUpperCase();
        transaction.setTransactionCode(uniqueKeyGenerator.getUniqueKeyGen());
        /* if (dbmerchantName.equals("FLIPKART")) {
            String targetUrl = transaction.getTargetUrl() + "&" + FLIPKART_AFFILIATE_KEY + "=" + merchant.getAffiliateId() + "&"
                    + FLIPKART_EXT_PARAMETER + "1=" + transaction.getId() + "&" + FLIPKART_EXT_PARAMETER + "2="
                    + transaction.getUsers().getId();
            transaction.setTargetUrl(targetUrl);
        }
        if (dbmerchantName.equals("SNAPDEAL")) {
            String targetUrl = transaction.getTargetUrl() + "&" + SNAPDEAL_AFFILIATE_KEY + "=" + merchant.getAffiliateId() + "&"
                    + SNAPDEAL_EXT_PARAMETER + "=" + transaction.getId() + "&" + SNAPDEAL_EXT_PARAMETER + "2="
                    + transaction.getUsers().getId();
            transaction.setTargetUrl(targetUrl);
        }*/
        trans = transactionRepository.save(transaction);
     String fQuery="?";
     
        if(transaction.getTargetUrl().indexOf('?')!=-1){
        	fQuery="&";
        }
        if (dbmerchantName.equals("FLIPKART")) {
            String targetUrl = transaction.getTargetUrl() + fQuery + FLIPKART_AFFILIATE_KEY + "=" + merchant.getAffiliateId() + "&"
                    + FLIPKART_EXT_PARAMETER + "1=" + trans.getId() + "&" + FLIPKART_EXT_PARAMETER + "2=" + transaction.getUsers().getId();
            transaction.setTargetUrl(targetUrl);
            trans = transactionRepository.save(transaction);
        }
        if (dbmerchantName.equals("SNAPDEAL")) {
            String targetUrl = transaction.getTargetUrl() + fQuery + SNAPDEAL_AFFILIATE_KEY + "=" + merchant.getAffiliateId() + "&"
                    + SNAPDEAL_EXT_PARAMETER + "=" + trans.getId() + "&" + SNAPDEAL_EXT_PARAMETER + "2=" + transaction.getUsers().getId();
            transaction.setTargetUrl(targetUrl);
            trans = transactionRepository.save(transaction);
        }
        
        return trans;
    }

    @Override
    public void deleteTransaction(final String transactionId) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Transaction> getAll() {
    	List<Transaction> transaction=(List<Transaction>) transactionRepository.findAll();
    	List<Transaction> transactions=new ArrayList<Transaction>();
    	for(Transaction trans:transaction){
    		Transaction tr=new Transaction();
    		tr.setAmount(trans.getAmount());
    		tr.setCashbackAmount(trans.getCashbackAmount());
    		tr.setCode(trans.getCode());
    		tr.setCreatedDate(trans.getCreatedDate());
    		tr.setId(trans.getId());
    		Merchant merchant=new Merchant();
    		merchant.setId(trans.getMerchant().getId());
    		tr.setMerchant(merchant);
    		tr.setOrderStatus(trans.getOrderStatus());
    		tr.setPaymentType(trans.getPaymentType());
    		tr.setRefundStatus(trans.getRefundStatus());
    		tr.setSource(trans.getSource());
    		tr.setTargetUrl(trans.getTargetUrl());
    		tr.setTransactionCount(trans.getTransactionCount());
    		tr.setTransactionErrorCode(trans.getTransactionErrorCode());
    		tr.setTransactionFor(trans.getTransactionFor());
    		tr.setTransactionMerchantErrorCode(trans.getTransactionMerchantErrorCode());
    		tr.setTransactionMerchantMsg(trans.getTransactionMerchantMsg());
    		Users users=new Users();
    		users.setId(trans.getUsers().getId());
    		users.setUserName(trans.getUsers().getUserName());
    		tr.setUsers(users);
    		transactions.add(tr);    		
    	}
    			return transactions;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ITransactionService#getDateTransaction(java.lang.String)
     */
    @Override
    public List<Transaction> getDateTransaction(final String dateId) {
        List<Transaction> transaction = new ArrayList();
        transaction = transactionRepository.findDateTransaction(dateId);
        return transaction;
    }

    /**
     * @return
     */
    @Override
    public Integer getMaxCode() {
        Integer i = transactionRepository.getMaxCode();
        return i;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ITransactionService#getMerchantTransaction(java.lang.String)
     */
    @Override
    public List<Transaction> getMerchantTransaction(final String merchantId) {
        List<Transaction> transaction = new ArrayList();
        transaction = transactionRepository.findMerchantTransaction(merchantId);
        return transaction;
    }

    @Override
    public Transaction getTransaction(final String transactionId) {
        // TODO Auto-generated method stub
        return transactionRepository.findOne(transactionId);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.ITransactionService#getUsersTransaction(java.lang.String)
     */
    @Override
    public List<Transaction> getUsersTransaction(final String usersId) {
        List<Transaction> transaction = new ArrayList();
        transaction = transactionRepository.findUserTransaction(usersId);
        return transaction;
    }

    @Override
    public Transaction updateTransaction(final Transaction transaction) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public List<Transaction> getFailTransactions() {
		
		List<Transaction> transaction=(List<Transaction>) transactionRepository.findByAll();
    	List<Transaction> transactions=new ArrayList<Transaction>();
    	
    	for(Transaction trans:transaction){
    		Transaction tr=new Transaction();
    		tr.setAmount(trans.getAmount());
    		tr.setCode(trans.getCode());
    		tr.setCreatedDate(trans.getCreatedDate());
    		tr.setOrderStatus(trans.getOrderStatus());
    		tr.setCashbackAmount(trans.getCashbackAmount());
    		tr.setId(trans.getId());
    		Merchant merchant=new Merchant();
    		merchant.setId(trans.getMerchant().getId());
    		tr.setMerchant(merchant);
    		Users users=new Users();
    		users.setId(trans.getUsers().getId());
    		users.setUserName(trans.getUsers().getUserName());
    		tr.setUsers(users);
    		transactions.add(tr);
    		
    	
    	
    	}
    			return transactions;
    		
	}


	/*@Override
	public List<Transaction> getAllTotalTransaction() {
		
		 List<Transaction> transaction = new ArrayList();
	        transaction = transactionRepository.findByAllTotalTransaction();
	        return transaction;
	}*/


}
