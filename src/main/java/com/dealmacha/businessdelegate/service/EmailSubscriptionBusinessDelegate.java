package com.dealmacha.businessdelegate.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.domain.EmailSubscription;
import com.dealmacha.model.EmailSubscriptionModel;
import com.dealmacha.service.IEmailSubscriptionService;

@Service
	public class EmailSubscriptionBusinessDelegate
	        implements IBusinessDelegate<EmailSubscriptionModel, EmailSubscriptionContext, IKeyBuilder<String>, String> {

	    @Autowired
	    private IEmailSubscriptionService emailSubscriptionService;
	    @Autowired
	    private ConversionService conversionService;

	    /**
	     * {@inheritDoc}
	     *
	     * @see com.meat.businessdelegate.service.IBusinessDelegate#create(com.meat.businessdelegate.domain.IModel)
	     */
	    @Override
	    public EmailSubscriptionModel create(EmailSubscriptionModel model) {
	        EmailSubscription emailSubscription = new EmailSubscription();
	        emailSubscription.setUserEmail(model.getUserEmail());
	        emailSubscription.setSubscriptionStatus("SUBSCRIBED");
	        emailSubscription.setCreatedDate(new Date());

	        if (model.getUserEmail() != null) {
	            EmailSubscription emailSub = new EmailSubscription();
	            emailSub = emailSubscriptionService.getEmailSubscriptionByEmail(model.getUserEmail());
	            if (emailSub != null) {
	                model.setSubscriptionStatus("ALSUBSCRIBED");
	                model.setId("NOT CREATED");
	                return model;
	            }
	            else {
	                emailSubscription = emailSubscriptionService.create(emailSubscription);
	                model.setSubscriptionStatus("SUBSCRIBED");
	                model.setId(emailSubscription.getId());
	            }
	        }
	       // model = conversionService.convert(emailSubscription, EmailSubscriptionModel.class);
	        return model;
	    }

	    /**
	     * {@inheritDoc}
	     *
	     * @see com.meat.businessdelegate.service.IBusinessDelegate#delete(com.meat.businessdelegate.domain.IKeyBuilder,
	     *      com.meat.businessdelegate.service.IBusinessDelegateContext)
	     */
	    @Override
	    public void delete(final IKeyBuilder<String> keyBuilder, final EmailSubscriptionContext context) {
	        // TODO Auto-generated method stub

	    }

	    /**
	     * {@inheritDoc}
	     *
	     * @see com.meat.businessdelegate.service.IBusinessDelegate#edit(com.meat.businessdelegate.domain.IKeyBuilder,
	     *      com.meat.businessdelegate.domain.IModel)
	     */
	    @Override
	    public EmailSubscriptionModel edit(final IKeyBuilder<String> keyBuilder, final EmailSubscriptionModel model) {
	        EmailSubscription emailSubscription = emailSubscriptionService.getEmailSubscription(keyBuilder.build().toString());
	        emailSubscription.setId(model.getId());

	        emailSubscription.setUserEmail(model.getUserEmail());
	        emailSubscription.setSubscriptionStatus(model.getSubscriptionStatus());
	        emailSubscription = emailSubscriptionService.updateEmailSubscription(emailSubscription);
	        return model;
	    }

	    /**
	     * {@inheritDoc}
	     *
	     * @see com.meat.businessdelegate.service.IBusinessDelegate#getByKey(com.meat.businessdelegate.domain.IKeyBuilder,
	     *      com.meat.businessdelegate.service.IBusinessDelegateContext)
	     */
	    @Override
	    public EmailSubscriptionModel getByKey(final IKeyBuilder<String> keyBuilder, final EmailSubscriptionContext context) {
	        EmailSubscription emailSubscription = emailSubscriptionService.getEmailSubscription(keyBuilder.build().toString());
	        EmailSubscriptionModel emailSubscriptionModel = conversionService.convert(emailSubscription, EmailSubscriptionModel.class);
	        return emailSubscriptionModel;
	    }

	    /**
	     * {@inheritDoc}
	     *
	     * @see com.meat.businessdelegate.service.IBusinessDelegate#getCollection(com.meat.businessdelegate.service.IBusinessDelegateContext)
	     */
	    @Override
	    public Collection<EmailSubscriptionModel> getCollection(final EmailSubscriptionContext context) {
	        List<EmailSubscription> emailSubscription = new ArrayList<EmailSubscription>();
	        if (context.getAll() != null) {
	            emailSubscription = emailSubscriptionService.getAll();
	        }
	        if (context.getAllEmails() != null) {
	            emailSubscription = emailSubscriptionService.getEmailSubscriptionSendsMail();

	        }
	        List<EmailSubscriptionModel> emailSubscriptionModels = (List<EmailSubscriptionModel>) conversionService.convert(emailSubscription,
	                TypeDescriptor.forObject(emailSubscription),
	                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(EmailSubscriptionModel.class)));
	        return emailSubscriptionModels;

	    }
}
