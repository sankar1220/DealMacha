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

import com.dealmacha.controller.AccountController;
import com.dealmacha.controller.AddressController;
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.AddressModel;
import com.dealmacha.resources.hal.AccountResource;
import com.dealmacha.resources.hal.AddressResource;

@Service
public class AccountResourceAssembler extends EmbeddableResourceAssemblerSupport<AccountModel, AccountResource, AccountController> {

    @Autowired
    public AccountResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, AccountController.class, AccountResource.class);
    }

    @Override
    public Link linkToSingleResource(final AccountModel accountModel) {
        return entityLinks.linkToSingleResource(AccountResource.class, accountModel.getId());
    }

    public AccountResource toDetailedResource(final AccountModel entity) {
        final AccountResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public AccountResource toResource(final AccountModel entity) {
        final AccountResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setAccountId(entity.getId());

        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();

        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
