package com.dealmacha.resources.assemblers;

import com.dealmacha.controller.AddressController;
import com.dealmacha.model.AddressModel;
import com.dealmacha.resources.hal.AddressResource;

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
public class AddressResourceAssembler extends EmbeddableResourceAssemblerSupport<AddressModel, AddressResource, AddressController> {

    @Autowired
    public AddressResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, AddressController.class, AddressResource.class);
    }

    @Override
    public Link linkToSingleResource(final AddressModel addressModel) {
        return entityLinks.linkToSingleResource(AddressResource.class, addressModel.getId());
    }

    public AddressResource toDetailedResource(final AddressModel entity) {
        final AddressResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public AddressResource toResource(final AddressModel entity) {
        final AddressResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setAddressId(entity.getId());

        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();

        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
