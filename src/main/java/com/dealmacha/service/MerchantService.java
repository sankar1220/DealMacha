package com.dealmacha.service;

import com.dealmacha.dao.MerchantRepository;
import com.dealmacha.domain.Merchant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MerchantService implements IMerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public Merchant create(final Merchant merchant) {
        // TODO Auto-generated method stub
        return merchantRepository.save(merchant);
    }

    @Override
    public void deleteMerchant(final String merchantId) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Merchant> getAll() {
      List<Merchant> merchant=(List<Merchant>) merchantRepository.findAll();
      List<Merchant> merchants=new ArrayList<Merchant>();
     
      for(Merchant mercha:merchant){
    	  Merchant merch=new Merchant();
    	  merch.setId(mercha.getId());
    	  merch.setAffiliateId(mercha.getAffiliateId());
    	  merch.setAffiliateKey(mercha.getAffiliateKey());
    	  merch.setDescription(mercha.getDescription());
    	  merch.setMerchantImage(mercha.getMerchantImage());
    	  merch.setMerchantName(mercha.getMerchantName());
merch.setMerchantToken(mercha.getMerchantToken());
merch.setUrl(mercha.getUrl());
merchants.add(mercha);

 
    }
        return merchants;
    }

    @Override
    public Merchant getMerchant(final String merchantId) {
Merchant merchant= merchantRepository.findOne(merchantId);
Merchant mer=new Merchant();
mer.setAffiliateId(merchant.getAffiliateId());
mer.setAffiliateKey(merchant.getAffiliateKey());
mer.setDescription(merchant.getDescription());
mer.setId(merchant.getId());
mer.setMerchantImage(merchant.getMerchantImage());
mer.setMerchantName(merchant.getMerchantName());
mer.setMerchantToken(merchant.getMerchantToken());
mer.setUrl(merchant.getUrl());
return merchant;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.service.IMerchantService#getMerchantByName(java.lang.String)
     */
    @Override
    public Merchant getMerchantByName(final String merchant) {
        // TODO Auto-generated method stub
        return merchantRepository.findMerchantByName(merchant);
    }

    @Override
    public Merchant updateMerchant(final Merchant merchant) {
        // TODO Auto-generated method stub
        return merchantRepository.save(merchant);
    }

}
