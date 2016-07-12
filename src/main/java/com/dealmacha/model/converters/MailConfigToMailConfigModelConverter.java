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
import com.dealmacha.domain.MailConfig;
import com.dealmacha.model.AddressModel;
import com.dealmacha.model.MailConfigModel;
import com.dealmacha.model.UsersModel;
import com.dealmacha.util.CollectionTypeDescriptor;

@Component("mailConfigToMailConfigModelConverter")
public class MailConfigToMailConfigModelConverter implements Converter<MailConfig, MailConfigModel> {
    @Autowired
    private ObjectFactory<MailConfigModel> mailConfigModelFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public MailConfigModel convert(final MailConfig source) {
    	MailConfigModel mailConfigModel = mailConfigModelFactory.getObject();
        BeanUtils.copyProperties(source, mailConfigModel);

  /*      if (CollectionUtils.isNotEmpty(source.getUsers())) {
            List<UsersModel> converted = (List<UsersModel>) conversionService.convert(source.getUsers(),
                    TypeDescriptor.forObject(source.getUsers()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), UsersModel.class));
            addressModel.getUsersModels().addAll(converted);
        }*/

        return mailConfigModel;
    }

    @Autowired
    public void setMailConfigModelFactory(final ObjectFactory<MailConfigModel> mailConfigModelFactory) {
        this.mailConfigModelFactory = mailConfigModelFactory;
    }

}