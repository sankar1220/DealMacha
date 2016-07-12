package com.dealmacha.resources.assemblers;

import com.dealmacha.controller.CmsSectionController;
import com.dealmacha.model.CmsSectionModel;
import com.dealmacha.resources.hal.CmsSectionResource;

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
public class CmsSectionResourceAssembler
        extends EmbeddableResourceAssemblerSupport<CmsSectionModel, CmsSectionResource, CmsSectionController> {

    @Autowired
    private CmsBlockResourceAssembler cmsBlockResourceAssembler;

    @Autowired
    public CmsSectionResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, CmsSectionController.class, CmsSectionResource.class);
    }

    @Override
    public Link linkToSingleResource(final CmsSectionModel CmsSectionModel) {
        return entityLinks.linkToSingleResource(CmsSectionResource.class, CmsSectionModel.getId());
    }

    public CmsSectionResource toDetailedResource(final CmsSectionModel entity) {
        final CmsSectionResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public CmsSectionResource toResource(final CmsSectionModel entity) {
        final CmsSectionResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setCmsSectionId(entity.getId());

        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();
        if (entity.getCmsBlockModels() != null) {
            embeddables.addAll(cmsBlockResourceAssembler.toEmbeddable(entity.getCmsBlockModels()));
        }
        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
