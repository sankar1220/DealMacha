/**
 *
 */
package com.dealmacha.resources.assemblers;

import com.dealmacha.controller.CmsPostsImagesController;
import com.dealmacha.model.CmsPostImagesModel;
import com.dealmacha.resources.hal.CmsPostsImagesResource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RelProvider;
import org.springframework.stereotype.Service;

/**
 * @author Naveen-PC
 *
 */
@Service
public class CmsPostsImagesResourceAssembler
        extends EmbeddableResourceAssemblerSupport<CmsPostImagesModel, CmsPostsImagesResource, CmsPostsImagesController> {

    /**
     * Constructor for CmsBlockResourceAssembler
     *
     * @param entityLinks
     * @param relProvider
     * @param controllerClass
     * @param resourceType
     */

    @Autowired
    public CmsPostsImagesResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, CmsPostsImagesController.class, CmsPostsImagesResource.class);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dealmacha.resources.assemblers.EmbeddableResourceAssemblerSupport#linkToSingleResource(java.lang.Object)
     */
    @Override
    public Link linkToSingleResource(final CmsPostImagesModel cmsPostImagesModel) {
        // TODO Auto-generated method stub
        return entityLinks.linkToSingleResource(CmsPostsImagesResource.class, CmsPostImagesModel.class);
    }

    public CmsPostsImagesResource toDetailedResource(final CmsPostImagesModel entity) {
        final CmsPostsImagesResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public CmsPostsImagesResource toResource(final CmsPostImagesModel entity) {
        final CmsPostsImagesResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setCmsPostImagesId(entity.getId());

        return resource;
    }

}
