package com.dealmacha.model.converters;

import com.dealmacha.domain.*;
import com.dealmacha.model.UsersModel;
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

@Component("usersModelToUsersConverter")
public class UsersModelToUsersConverter implements Converter<UsersModel, Users> {
    @Autowired
    private ObjectFactory<Users> usersFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public Users convert(final UsersModel source) {
        Users users = usersFactory.getObject();
        BeanUtils.copyProperties(source, users);

        if (CollectionUtils.isNotEmpty(source.getUserAddressModel())) {
            List<UserAddress> converted = (List<UserAddress>) conversionService.convert(source.getUserAddressModel(),
                    TypeDescriptor.forObject(source.getUserAddressModel()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), UserAddress.class));
            users.getUserAddress().addAll(converted);
        }
        if (CollectionUtils.isNotEmpty(source.getRolesModels())) {
            List<Roles> converted = (List<Roles>) conversionService.convert(source.getRolesModels(),
                    TypeDescriptor.forObject(source.getRolesModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), Roles.class));
            users.getRoles().addAll(converted);
        }
        if (CollectionUtils.isNotEmpty(source.getRequestsModels())) {
            List<Requests> converted = (List<Requests>) conversionService.convert(source.getRequestsModels(),
                    TypeDescriptor.forObject(source.getRequestsModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), Requests.class));
            users.getRequests().addAll(converted);
        }
        if (CollectionUtils.isNotEmpty(source.getTransactionModels())) {
            List<Transaction> converted = (List<Transaction>) conversionService.convert(source.getTransactionModels(),
                    TypeDescriptor.forObject(source.getTransactionModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), Transaction.class));
            users.getTransactions().addAll(converted);
        }
        if (CollectionUtils.isNotEmpty(source.getClickTransactionModels())) {
            List<ClickTransaction> converted = (List<ClickTransaction>) conversionService.convert(source.getClickTransactionModels(),
                    TypeDescriptor.forObject(source.getClickTransactionModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), ClickTransaction.class));
            users.getClickTransactions().addAll(converted);
        }
        if (CollectionUtils.isNotEmpty(source.getCashbackTransactionModels())) {
            List<CashbackTransaction> converted = (List<CashbackTransaction>) conversionService.convert(source.getCashbackTransactionModels(),
                    TypeDescriptor.forObject(source.getCashbackTransactionModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), CashbackTransaction.class));
            users.getCashbackTransactions().addAll(converted);
        }
        if (CollectionUtils.isNotEmpty(source.getAccountModels())) {
            List<Account> converted = (List<Account>) conversionService.convert(source.getAccountModels(),
                    TypeDescriptor.forObject(source.getAccountModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), Account.class));
            users.getAccounts().addAll(converted);
        }
        return users;
    }

    @Autowired
    public void setUsersFactory(final ObjectFactory<Users> usersFactory) {
        this.usersFactory = usersFactory;
    }

}