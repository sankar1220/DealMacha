package com.dealmacha.resources.assemblers;

import com.dealmacha.controller.UserAddressController;
import com.dealmacha.model.UserAddressModel;
import com.dealmacha.resources.hal.UserAddressResource;

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
public class UserAddressResourceAssembler extends
        EmbeddableResourceAssemblerSupport<UserAddressModel, UserAddressResource, UserAddressController> {

    @Autowired
    public UserAddressResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, UserAddressController.class, UserAddressResource.class);
    }

    @Override
    public Link linkToSingleResource(final UserAddressModel userAddressModel) {
        return entityLinks.linkToSingleResource(UserAddressResource.class, userAddressModel.getId());
    }

    public UserAddressResource toDetailedResource(final UserAddressModel entity) {
        final UserAddressResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public UserAddressResource toResource(final UserAddressModel entity) {
        System.out.println("vbsdbd" + entity.getId());
        final UserAddressResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setUserAddressId(entity.getId());

        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();

        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
