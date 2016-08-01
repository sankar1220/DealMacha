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

import com.dealmacha.controller.RequestTypeAttributesController;
import com.dealmacha.model.RequestTypeAttributesModel;
import com.dealmacha.resources.hal.RequestTypeAttributesResource;

	@Service
	public class RequestTypeAttributesResourceAssembler extends EmbeddableResourceAssemblerSupport<RequestTypeAttributesModel, RequestTypeAttributesResource, RequestTypeAttributesController> {

	    @Autowired
	    public RequestTypeAttributesResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
	        super(entityLinks, relProvider, RequestTypeAttributesController.class, RequestTypeAttributesResource.class);
	    }

	    @Override
	    public Link linkToSingleResource(final RequestTypeAttributesModel requestTypeAttributesModel) {
	        return entityLinks.linkToSingleResource(RequestTypeAttributesResource.class, requestTypeAttributesModel.getId());
	    }

	    public RequestTypeAttributesResource toDetailedResource(final RequestTypeAttributesModel entity) {
	        final RequestTypeAttributesResource resource = createResourceWithId(entity.getId(), entity);

	        return resource;
	    }

	    @Override
	    public RequestTypeAttributesResource toResource(final RequestTypeAttributesModel entity) {
	        final RequestTypeAttributesResource resource = createResourceWithId(entity.getId(), entity);
	        BeanUtils.copyProperties(entity, resource);
	        resource.setRequestTypeAttributesId(entity.getId());

	        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();

	        resource.setEmbeddeds(new Resources<>(embeddables));
	        return resource;
	    }
}
