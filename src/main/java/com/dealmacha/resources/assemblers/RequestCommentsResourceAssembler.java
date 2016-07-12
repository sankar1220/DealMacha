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

import com.dealmacha.controller.RequestCommentsController;
import com.dealmacha.model.RequestCommentsModel;
import com.dealmacha.resources.hal.RequestCommentsResource;

@Service
public class RequestCommentsResourceAssembler extends EmbeddableResourceAssemblerSupport<RequestCommentsModel, RequestCommentsResource, RequestCommentsController> {

    @Autowired
    public RequestCommentsResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, RequestCommentsController.class, RequestCommentsResource.class);
    }

    @Override
    public Link linkToSingleResource(final RequestCommentsModel requestCommentsModel) {
        return entityLinks.linkToSingleResource(RequestCommentsResource.class, requestCommentsModel.getId());
    }

    public RequestCommentsResource toDetailedResource(final RequestCommentsModel entity) {
        final RequestCommentsResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public RequestCommentsResource toResource(final RequestCommentsModel entity) {
        final RequestCommentsResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setRequestCommentsId(entity.getId());

        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();

        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }


}
