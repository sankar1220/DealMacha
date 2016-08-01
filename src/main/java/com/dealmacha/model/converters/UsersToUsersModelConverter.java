package com.dealmacha.model.converters;

import com.dealmacha.domain.Requests;
import com.dealmacha.domain.Users;
import com.dealmacha.model.*;
import com.dealmacha.util.CollectionTypeDescriptor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component("usersToUsersModelConverter")
public class UsersToUsersModelConverter implements Converter<Users, UsersModel> {
    @Autowired
    private ObjectFactory<UsersModel> usersModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public UsersModel convert(final Users source) {
        UsersModel usersModel = usersModelFactory.getObject();
        BeanUtils.copyProperties(source, usersModel);
        if (source.getId() == null) {
            usersModel.setId("USER NOT CREATED");
        }
        String ds1 = null;
        if (source.getCreatedDate() != null) {
            ds1 = source.getCreatedDate().toString();
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        if (ds1 != null) {
            try {
                String ds2 = sdf2.format(source.getCreatedDate());
                usersModel.setCreatedDate(ds2);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        usersModel.setUserCount(Integer.toString(source.getUserCount()));

        if (CollectionUtils.isNotEmpty(source.getUserAddress())) {
            List<UserAddressModel> converted = (List<UserAddressModel>) conversionService.convert(source.getUserAddress(),
                    TypeDescriptor.forObject(source.getUserAddress()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), UserAddressModel.class));
            usersModel.getUserAddressModel().addAll(converted);
        }
        if (CollectionUtils.isNotEmpty(source.getRoles())) {
            List<RolesModel> converted = (List<RolesModel>) conversionService.convert(source.getRoles(),
                    TypeDescriptor.forObject(source.getRoles()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), RolesModel.class));
            usersModel.getRolesModels().addAll(converted);
        }
        if (CollectionUtils.isNotEmpty(source.getRequests())) {
            List<RequestsModel> converted = (List<RequestsModel>) conversionService.convert(source.getRequests(),
                    TypeDescriptor.forObject(source.getRequests()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), RequestsModel.class));
            usersModel.getRequestsModels().addAll(converted);
        }
        if (CollectionUtils.isNotEmpty(source.getTransactions())) {
            List<TransactionModel> converted = (List<TransactionModel>) conversionService.convert(source.getTransactions(),
                    TypeDescriptor.forObject(source.getTransactions()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), TransactionModel.class));
            usersModel.getTransactionModels().addAll(converted);

        }

        if (CollectionUtils.isNotEmpty(source.getClickTransactions())) {
            List<ClickTransactionModel> converted = (List<ClickTransactionModel>) conversionService.convert(source.getClickTransactions(),
                    TypeDescriptor.forObject(source.getClickTransactions()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), ClickTransactionModel.class));
            usersModel.getClickTransactionModels().addAll(converted);

        }
        if (CollectionUtils.isNotEmpty(source.getAccounts())) {
            List<AccountModel> converted = (List<AccountModel>) conversionService.convert(source.getAccounts(),
                    TypeDescriptor.forObject(source.getAccounts()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), AccountModel.class));
            usersModel.getAccountModels().addAll(converted);

        }
        if (CollectionUtils.isNotEmpty(source.getRequests())) {
            List<RequestsModel> converted = (List<RequestsModel>) conversionService.convert(source.getRequests(),
                    TypeDescriptor.forObject(source.getRequests()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), RequestsModel.class));
            usersModel.getRequestsModels().addAll(converted);

        }
        return usersModel;
    }

    @Autowired
    public void setUsersModelFactory(final ObjectFactory<UsersModel> usersModelFactory) {
        this.usersModelFactory = usersModelFactory;
    }

}