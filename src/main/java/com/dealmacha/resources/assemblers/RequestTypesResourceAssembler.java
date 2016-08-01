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

import com.dealmacha.controller.RequestTypesController;
import com.dealmacha.model.RequestTypesModel;
import com.dealmacha.resources.hal.RequestTypesResource;

@Service
public class RequestTypesResourceAssembler extends EmbeddableResourceAssemblerSupport<RequestTypesModel, RequestTypesResource, RequestTypesController> {

	 @Autowired
	 private RequestTypeAttributesResourceAssembler requestTypeAttributesResourceAssembler;
	 @Autowired
	 private RequestsResourceAssembler requestsResourceAssembler;
    @Autowired
    public RequestTypesResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, RequestTypesController.class, RequestTypesResource.class);
    }

    @Override
    public Link linkToSingleResource(final RequestTypesModel requestTypesModel) {
        return entityLinks.linkToSingleResource(RequestTypesResource.class, requestTypesModel.getId());
    }

    public RequestTypesResource toDetailedResource(final RequestTypesModel entity) {
        final RequestTypesResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public RequestTypesResource toResource(final RequestTypesModel entity) {
        final RequestTypesResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setRequestTypesId(entity.getId());

        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();
        if (entity.getRequestTypeAttributesModels() != null) {
            embeddables.addAll(requestTypeAttributesResourceAssembler.toEmbeddable(entity.getRequestTypeAttributesModels()));
        }
        if(entity.getRequestsModels()!=null){
        	embeddables.addAll(requestsResourceAssembler.toEmbeddable(entity.getRequestsModels()));
        }
        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }


}
