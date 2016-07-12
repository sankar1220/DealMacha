/**
 *
 */
package com.dealmacha.resources.assemblers;

import com.dealmacha.controller.OffersController;
import com.dealmacha.model.OffersModel;
import com.dealmacha.resources.hal.OffersResource;

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

/**
 * @author arthvedi5
 *
 */
@Service
public class OffersResourceAssembler extends EmbeddableResourceAssemblerSupport<OffersModel, OffersResource, OffersController> {

    @Autowired
    public OffersResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, OffersController.class, OffersResource.class);
    }

    @Override
    public Link linkToSingleResource(final OffersModel offersModel) {
        return entityLinks.linkToSingleResource(OffersResource.class, offersModel.getId());
    }

    public OffersResource toDetailedResource(final OffersModel entity) {
        final OffersResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public OffersResource toResource(final OffersModel entity) {
        final OffersResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setOffersId(entity.getId());

        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();

        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
