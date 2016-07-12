package com.dealmacha.service;

import java.util.List;

import com.dealmacha.domain.MailConfig;
import com.dealmacha.domain.Payment;

public interface IPaymentService {
	Payment create(Payment payment);

    void deletePayment(String paymentId);

    /**
     * @param string
     * @return
     */
    Payment getPayment(String paymentId);

    List<Payment> getAll();



    Payment updatePayment(Payment payment);

    

}
