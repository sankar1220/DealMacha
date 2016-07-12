/**
 *
 */
package com.dealmacha.model.converters;

import com.dealmacha.domain.Payment;
import com.dealmacha.domain.Transaction;
import com.dealmacha.model.TransactionModel;
import com.dealmacha.util.CollectionTypeDescriptor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author mohan
 *
 */
@Component("transactionModelToTransactionConverter")
public class TransactionModelToTransactionConverter implements Converter<TransactionModel, Transaction> {
    @Autowired
    private ObjectFactory<Transaction> transactionFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public Transaction convert(final TransactionModel source) {
        Transaction transaction = transactionFactory.getObject();
        BeanUtils.copyProperties(source, transaction);

        if (CollectionUtils.isNotEmpty(source.getPaymentModels())) {
            List<Payment> converted = (List<Payment>) conversionService.convert(source.getPaymentModels(),
                    TypeDescriptor.forObject(source.getPaymentModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), Payment.class));
            transaction.getPayments().addAll(converted);
        }

        
        return transaction;
    }

    @Autowired
    public void setTransactionFactory(final ObjectFactory<Transaction> transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

}
