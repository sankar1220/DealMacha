/**
 *
 */
package com.dealmacha.businessdelegate.service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.MailConfig;
import com.dealmacha.model.MailConfigModel;
import com.dealmacha.service.IMailConfigService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

/**
 * @author arthvedi
 *
 */

@Service
public class MailConfigBusinessDelegate implements IBusinessDelegate<MailConfigModel, MailConfigContext, IKeyBuilder<String>, String> {

    @Autowired
    private IMailConfigService mailConfigService;
    @Autowired
    private ConversionService conversionService;

    /**
     * {@inheritDoc}
     *
     * @see com.meat.businessdelegate.service.IBusinessDelegate#create(com.meat.businessdelegate.domain.IModel)
     */
    @Override
    public MailConfigModel create(MailConfigModel model) {
        MailConfig mailConfig = new MailConfig();
        mailConfig.setMailAttributeName(model.getMailAttributeName());
        mailConfig.setMailAttributeValue(model.getMailAttributeValue());
        mailConfig.setStatus(model.getStatus());
        mailConfig.setCreatedDate(new Date());
        mailConfig = mailConfigService.create(mailConfig);
        model = conversionService.convert(mailConfig, MailConfigModel.class);
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.businessdelegate.service.IBusinessDelegate#delete(com.meat.businessdelegate.domain.IKeyBuilder,
     *      com.meat.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public void delete(final IKeyBuilder<String> keyBuilder, final MailConfigContext context) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.businessdelegate.service.IBusinessDelegate#edit(com.meat.businessdelegate.domain.IKeyBuilder,
     *      com.meat.businessdelegate.domain.IModel)
     */
    @Override
    public MailConfigModel edit(final IKeyBuilder<String> keyBuilder, final MailConfigModel model) {
        MailConfig mailConfig = mailConfigService.getMailConfig(keyBuilder.build().toString());
        mailConfig.setId(model.getId());
        if (model.getMailAttributeName() != null) {
            mailConfig.setMailAttributeName(model.getMailAttributeName());
        }
        if (model.getMailAttributeValue() != null) {
            mailConfig.setMailAttributeValue(model.getMailAttributeValue());
        }
        if (model.getStatus() != null) {
            mailConfig.setStatus(model.getStatus());
        }
        mailConfig.setModifiedDate(new Date());
        mailConfig = mailConfigService.updateMailConfig(mailConfig);
        model.setId(mailConfig.getId());
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.businessdelegate.service.IBusinessDelegate#getByKey(com.meat.businessdelegate.domain.IKeyBuilder,
     *      com.meat.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public MailConfigModel getByKey(final IKeyBuilder<String> keyBuilder, final MailConfigContext context) {
        MailConfig mailConfig = mailConfigService.getMailConfig(keyBuilder.build().toString());
        MailConfigModel mailConfigModel = conversionService.convert(mailConfig, MailConfigModel.class);
        return mailConfigModel;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.businessdelegate.service.IBusinessDelegate#getCollection(com.meat.businessdelegate.service.IBusinessDelegateContext)
     */
    @Override
    public Collection<MailConfigModel> getCollection(final MailConfigContext context) {
        List<MailConfig> mailConfig = new ArrayList<MailConfig>();

        if (context.getAll() != null) {
            mailConfig = mailConfigService.getAll();
        }
        List<MailConfigModel> mailConfigModels = (List<MailConfigModel>) conversionService.convert(mailConfig,
                TypeDescriptor.forObject(mailConfig), TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(MailConfigModel.class)));
        return mailConfigModels;
    }

}
