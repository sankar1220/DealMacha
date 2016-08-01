/**
 *
 */
package com.dealmacha.model.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dealmacha.domain.EmailSubscription;
import com.dealmacha.model.EmailSubscriptionModel;

/**
 * @author varma
 *
 */
@Component("emailSubscriptionToEmailSubscriptionModelConverter")
public class EmailSubscriptionToEmailSubscriptionModelConverter implements Converter<EmailSubscription, EmailSubscriptionModel> {

    private static final Logger LOGGER = Logger.getLogger(EmailSubscriptionToEmailSubscriptionModelConverter.class);
    @Autowired
    private ObjectFactory<EmailSubscriptionModel> emailSubscriptionModelFactory;
    @Autowired
    private ConversionService conversionService;

    /**
     * {@inheritDoc}
     *
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public EmailSubscriptionModel convert(final EmailSubscription source) {
        // TODO Auto-generated method stub
        EmailSubscriptionModel emailSubscriptionModel = emailSubscriptionModelFactory.getObject();

        BeanUtils.copyProperties(source, emailSubscriptionModel);

        return emailSubscriptionModel;

    }

    @Autowired
    public void setEmailSubscriptionFactory(final ObjectFactory<EmailSubscriptionModel> emailSubscriptionModelFactory) {
        this.emailSubscriptionModelFactory = emailSubscriptionModelFactory;
    }

}
