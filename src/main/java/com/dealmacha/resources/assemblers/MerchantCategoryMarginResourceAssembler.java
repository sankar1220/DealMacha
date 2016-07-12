/**
 *
 */
package com.dealmacha.resources.assemblers;

import com.dealmacha.controller.MerchantCategoryMarginController;
import com.dealmacha.model.MerchantCategoryMarginModel;
import com.dealmacha.resources.hal.MerchantCategoryMarginResource;

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
public class MerchantCategoryMarginResourceAssembler extends
EmbeddableResourceAssemblerSupport<MerchantCategoryMarginModel, MerchantCategoryMarginResource, MerchantCategoryMarginController> {

    @Autowired
    public MerchantCategoryMarginResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, MerchantCategoryMarginController.class, MerchantCategoryMarginResource.class);
    }

    @Override
    public Link linkToSingleResource(final MerchantCategoryMarginModel merchantCategoryMarginModel) {
        return entityLinks.linkToSingleResource(MerchantCategoryMarginResource.class, merchantCategoryMarginModel.getId());
    }

    public MerchantCategoryMarginResource toDetailedResource(final MerchantCategoryMarginModel entity) {
        final MerchantCategoryMarginResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public MerchantCategoryMarginResource toResource(final MerchantCategoryMarginModel entity) {

        final MerchantCategoryMarginResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);

        resource.setMerchantCategoryMarginId(entity.getId());
        resource.setChannel(entity.getChannel());
        resource.setCustomerType(entity.getChannel());
        resource.setCommission(entity.getCommission());
        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();

        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
