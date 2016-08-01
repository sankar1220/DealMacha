package com.dealmacha.resources.assemblers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.stereotype.Service;

import com.dealmacha.controller.EmailSubscriptionController;
import com.dealmacha.model.EmailSubscriptionModel;
import com.dealmacha.resources.hal.EmailSubscriptionResource;

	@Service
	public class EmailSubscriptionResourceAssembler extends EmbeddableResourceAssemblerSupport<EmailSubscriptionModel, EmailSubscriptionResource, EmailSubscriptionController> {

	    @Autowired
	    public EmailSubscriptionResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
	        super(entityLinks, relProvider, EmailSubscriptionController.class, EmailSubscriptionResource.class);
	    }

	    @Override
	    public Link linkToSingleResource(final EmailSubscriptionModel emailSubscriptionModel) {
	        return entityLinks.linkToSingleResource(EmailSubscriptionResource.class, emailSubscriptionModel.getId());
	    }

	    public EmailSubscriptionResource toDetailedResource(final EmailSubscriptionModel entity) {
	        final EmailSubscriptionResource resource = createResourceWithId(entity.getId(), entity);

	        return resource;
	    }

	    @Override
	    public EmailSubscriptionResource toResource(final EmailSubscriptionModel entity) {
	        final EmailSubscriptionResource resource = createResourceWithId(entity.getId(), entity);
	        BeanUtils.copyProperties(entity, resource);
	        resource.setEmailSubscriptionId(entity.getId());
	        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();
	        resource.setEmbeddeds(new Resources<>(embeddables));
	        return resource;
	    }


}
