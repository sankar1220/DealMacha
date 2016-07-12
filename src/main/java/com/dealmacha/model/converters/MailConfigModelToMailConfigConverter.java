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
import com.dealmacha.domain.Users;
import com.dealmacha.model.AddressModel;
import com.dealmacha.model.MailConfigModel;
import com.dealmacha.util.CollectionTypeDescriptor;

@Component("mailConfigModelToMailConfigConverter")
public class MailConfigModelToMailConfigConverter implements Converter<MailConfigModel, MailConfig> {
    @Autowired
    private ObjectFactory<MailConfig> mailConfigFactory;
    @Autowired
    private ConversionService conversionService;

    @Override
    public MailConfig convert(final MailConfigModel source) {
    	MailConfig mailConfig= mailConfigFactory.getObject();
        BeanUtils.copyProperties(source, mailConfig);

 /*       if (CollectionUtils.isNotEmpty(source.getUsersModels())) {
            List<Users> converted = (List<Users>) conversionService.convert(source.getUsersModels(),
                    TypeDescriptor.forObject(source.getUsersModels()),
                    CollectionTypeDescriptor.forType(TypeDescriptor.valueOf(List.class), Users.class));
            address.getUsers().addAll(converted);
        }*/

        return mailConfig;
    }

    @Autowired
    public void setMailConfigFactory(final ObjectFactory<MailConfig> mailConfigFactory) {
        this.mailConfigFactory = mailConfigFactory;
    }

}