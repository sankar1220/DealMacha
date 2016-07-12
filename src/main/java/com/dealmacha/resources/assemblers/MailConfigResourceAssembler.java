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

import com.dealmacha.controller.AddressController;
import com.dealmacha.controller.MailConfigController;
import com.dealmacha.model.AddressModel;
import com.dealmacha.model.MailConfigModel;
import com.dealmacha.resources.hal.AddressResource;
import com.dealmacha.resources.hal.MailConfigResource;

@Service
public class MailConfigResourceAssembler extends EmbeddableResourceAssemblerSupport<MailConfigModel, MailConfigResource, MailConfigController> {

    @Autowired
    public MailConfigResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, MailConfigController.class, MailConfigResource.class);
    }

    @Override
    public Link linkToSingleResource(final MailConfigModel mailConfigModel) {
        return entityLinks.linkToSingleResource(MailConfigResource.class, mailConfigModel.getId());
    }

    public MailConfigResource toDetailedResource(final MailConfigModel entity) {
        final MailConfigResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public MailConfigResource toResource(final MailConfigModel entity) {
        final MailConfigResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setMailConfigId(entity.getId());

        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();

        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }


}
