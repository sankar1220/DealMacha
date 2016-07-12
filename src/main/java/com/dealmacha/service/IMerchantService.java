package com.dealmacha.service;

import com.dealmacha.domain.Merchant;

import java.util.List;

public interface IMerchantService {
    Merchant create(Merchant merchant);

    void deleteMerchant(String merchantId);

    List<Merchant> getAll();

    Merchant getMerchant(String merchantId);

    /**
     * @param merchant
     * @return
     */
    Merchant getMerchantByName(String merchant);

    Merchant updateMerchant(Merchant merchant);

}
