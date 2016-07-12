/**
 *
 */
package com.dealmacha.resources.assemblers;

import com.dealmacha.controller.MerchantCategoryController;
import com.dealmacha.model.MerchantCategoryModel;
import com.dealmacha.resources.hal.MerchantCategoryResource;

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
 * @author arthvedi5
 *
 */
@Service
public class MerchantCategoryResourceAssembler extends
EmbeddableResourceAssemblerSupport<MerchantCategoryModel, MerchantCategoryResource, MerchantCategoryController> {
    @Autowired
    private MerchantCategoryMarginResourceAssembler merchantCategoryMarginResourceAssembler;

    @Autowired
    public MerchantCategoryResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, MerchantCategoryController.class, MerchantCategoryResource.class);
    }

    @Override
    public Link linkToSingleResource(final MerchantCategoryModel merchantCategoryModel) {
        return entityLinks.linkToSingleResource(MerchantCategoryResource.class, merchantCategoryModel.getId());
    }

    public MerchantCategoryResource toDetailedResource(final MerchantCategoryModel entity) {
        final MerchantCategoryResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public MerchantCategoryResource toResource(final MerchantCategoryModel entity) {
        final MerchantCategoryResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setMerchantCategoryId(entity.getId());

        // Add (multiple) links to authored books
        /*resource.add(relProvider.);*/
        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();
        if (entity.getMerchantCategoryMarginModel() != null) {
            embeddables.addAll(merchantCategoryMarginResourceAssembler.toEmbeddable(entity.getMerchantCategoryMarginModel()));
        }
        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
