/**
 *
 */
package com.dealmacha.resources.assemblers;

import com.dealmacha.controller.CmsPostsController;
import com.dealmacha.model.CmsBlockModel;
import com.dealmacha.model.CmsPostsModel;
import com.dealmacha.resources.hal.CmsBlockResource;
import com.dealmacha.resources.hal.CmsPostsResource;

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
public class CmsPostsResourceAssembler extends EmbeddableResourceAssemblerSupport<CmsPostsModel, CmsPostsResource, CmsPostsController> {

    /**
     * Constructor for CmsBlockResourceAssembler
     *
     * @param entityLinks
     * @param relProvider
     * @param controllerClass
     * @param resourceType
     */
    @Autowired
    private CmsPostsImagesResourceAssembler cmsPostsImagesResourceAssembler;

    @Autowired
    public CmsPostsResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, CmsPostsController.class, CmsPostsResource.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.resources.assemblers.EmbeddableResourceAssemblerSupport#linkToSingleResource(java.lang.Object)
     */
    @Override
    public Link linkToSingleResource(final CmsPostsModel cmsPostsModel) {
        // TODO Auto-generated method stub
        return entityLinks.linkToSingleResource(CmsBlockResource.class, CmsBlockModel.class);
    }

    public CmsPostsResource toDetailedResource(final CmsPostsModel entity) {
        final CmsPostsResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public CmsPostsResource toResource(final CmsPostsModel entity) {
        final CmsPostsResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setCmsPostsId(entity.getId());
        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();
        if (entity.getCmsPostImageModel() != null) {
            embeddables.addAll(cmsPostsImagesResourceAssembler.toEmbeddable(entity.getCmsPostImageModel()));
        }
        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
