/**
 *
 */
package com.dealmacha.resources.assemblers;

import com.dealmacha.controller.CmsBlockController;
import com.dealmacha.model.CmsBlockModel;
import com.dealmacha.resources.hal.CmsBlockResource;

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
 * @author Naveen-PC
 *
 */
@Service
public class CmsBlockResourceAssembler extends EmbeddableResourceAssemblerSupport<CmsBlockModel, CmsBlockResource, CmsBlockController> {

    /**
     * Constructor for CmsBlockResourceAssembler
     *
     * @param entityLinks
     * @param relProvider
     * @param controllerClass
     * @param resourceType
     */
    @Autowired
    private CmsPostsResourceAssembler cmsPostsResourceAssembler;

    @Autowired
    public CmsBlockResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, CmsBlockController.class, CmsBlockResource.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.resources.assemblers.EmbeddableResourceAssemblerSupport#linkToSingleResource(java.lang.Object)
     */
    @Override
    public Link linkToSingleResource(final CmsBlockModel cmsBlockModel) {
        // TODO Auto-generated method stub
        return entityLinks.linkToSingleResource(CmsBlockResource.class, CmsBlockModel.class);
    }

    public CmsBlockResource toDetailedResource(final CmsBlockModel entity) {
        final CmsBlockResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public CmsBlockResource toResource(final CmsBlockModel entity) {
        final CmsBlockResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setCmsBlockId(entity.getId());

        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();
        if (entity.getCmsPostsModels() != null) {
            embeddables.addAll(cmsPostsResourceAssembler.toEmbeddable(entity.getCmsPostsModels()));
        }
        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
