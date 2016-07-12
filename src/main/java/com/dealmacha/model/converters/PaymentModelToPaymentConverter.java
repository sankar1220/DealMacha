package com.dealmacha.model.converters;

import com.dealmacha.domain.Payment;
import com.dealmacha.model.PaymentModel;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("paymentModelToPaymentConverter")
public class PaymentModelToPaymentConverter implements Converter<PaymentModel, Payment> {
    @Autowired
    private ObjectFactory<Payment> paymentFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public Payment convert(final PaymentModel source) {
        Payment payment = paymentFactory.getObject();
        BeanUtils.copyProperties(source, payment);

        /*if (CollectionUtils.isNotEmpty(source.getUsersModels())) {
            List<Users> converted = (List<Users>) conversionService.convert(source.getUsersModels(),
                    TypeDescriptor.forObject(source.getUsersModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), Users.class));
            address.getUsers().addAll(converted);
        }*/

        return payment;
    }

    @Autowired
    public void setPaymentFactory(final ObjectFactory<Payment> paymentFactory) {
        this.paymentFactory = paymentFactory;
    }

}