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

import com.dealmacha.controller.RequestsController;
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.RequestsModel;
import com.dealmacha.resources.hal.AccountResource;
import com.dealmacha.resources.hal.RequestsResource;

@Service
public class RequestsResourceAssembler extends EmbeddableResourceAssemblerSupport<RequestsModel, RequestsResource, RequestsController> {

    @Autowired
    public RequestsResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, RequestsController.class, RequestsResource.class);
    }

    @Override
    public Link linkToSingleResource(final RequestsModel requestsModel) {
        return entityLinks.linkToSingleResource(AccountResource.class, requestsModel.getId());
    }

    public RequestsResource toDetailedResource(final RequestsModel entity) {
        final RequestsResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public RequestsResource toResource(final RequestsModel entity) {
        final RequestsResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setRequestsId(entity.getId());

        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();

        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
