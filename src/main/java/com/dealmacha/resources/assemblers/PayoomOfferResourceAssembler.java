/**
 *
 */
package com.dealmacha.resources.assemblers;

import com.dealmacha.controller.PayoomOfferController;
import com.dealmacha.model.PayoomOfferModel;
import com.dealmacha.resources.hal.PayoomOfferResource;

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

@Service
public class PayoomOfferResourceAssembler extends
        EmbeddableResourceAssemblerSupport<PayoomOfferModel, PayoomOfferResource, PayoomOfferController> {

    @Autowired
    public PayoomOfferResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, PayoomOfferController.class, PayoomOfferResource.class);
    }

    @Override
    public Link linkToSingleResource(final PayoomOfferModel payoomOfferModel) {
        return entityLinks.linkToSingleResource(PayoomOfferResource.class, payoomOfferModel.getId());
    }

    public PayoomOfferResource toDetailedResource(final PayoomOfferModel entity) {
        final PayoomOfferResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public PayoomOfferResource toResource(final PayoomOfferModel entity) {
        if (entity.getId() == null) {
            entity.setId("PayoomOffer Not Created");
        }
        final PayoomOfferResource resource = createResourceWithId(entity.getId(), entity);

        BeanUtils.copyProperties(entity, resource);

        resource.setPayoomOfferId(entity.getId());

        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();

        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
