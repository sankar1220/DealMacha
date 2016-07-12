package com.dealmacha.model.converters;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.Address;
import com.dealmacha.domain.Payment;
import com.dealmacha.model.PaymentModel;
import com.dealmacha.util.CollectionTypeDescriptor;

@Component("paymentToPaymentModelConverter")
public class PaymentToPaymentModelConverter implements Converter<Payment, PaymentModel> {
    @Autowired
    private ObjectFactory<PaymentModel> paymentModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public PaymentModel convert(final Payment source) {
        PaymentModel paymentModel = paymentModelFactory.getObject();
        BeanUtils.copyProperties(source, paymentModel);

        /*if (CollectionUtils.isNotEmpty(source.getUsers())) {
            List<UsersModel> converted = (List<UsersModel>) conversionService.convert(source.getUsers(),
                    TypeDescriptor.forObject(source.getUsers()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), UsersModel.class));
            addressModel.getUsersModels().addAll(converted);
        }
*/
        return paymentModel;
    }

    @Autowired
    public void setPaymentModelFactory(final ObjectFactory<PaymentModel> paymentModelFactory) {
        this.paymentModelFactory = paymentModelFactory;
    }

}