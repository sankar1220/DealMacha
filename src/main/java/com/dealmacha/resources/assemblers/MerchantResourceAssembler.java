package com.dealmacha.resources.assemblers;

import com.dealmacha.controller.MerchantController;
import com.dealmacha.model.MerchantModel;
import com.dealmacha.resources.hal.MerchantResource;

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
public class MerchantResourceAssembler extends EmbeddableResourceAssemblerSupport<MerchantModel, MerchantResource, MerchantController> {

    @Autowired
    private MerchantCategoryResourceAssembler merchantCategoryResourceAssembler;
    @Autowired
    private TransactionResourceAssembler transactionResourceAssembler;

    @Autowired
    public MerchantResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, MerchantController.class, MerchantResource.class);
    }

    @Override
    public Link linkToSingleResource(final MerchantModel merchantModel) {
        return entityLinks.linkToSingleResource(MerchantResource.class, merchantModel.getId());
    }

    public MerchantResource toDetailedResource(final MerchantModel entity) {
        final MerchantResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public MerchantResource toResource(final MerchantModel entity) {
        final MerchantResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setMerchantId(entity.getId());
resource.setAffliateId(entity.getAffiliateId());
        // Add (multiple) links to authored books
        /*resource.add(relProvider.);*/
        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();
        if (entity.getMerchantCategoryModel() != null) {
            embeddables.addAll(merchantCategoryResourceAssembler.toEmbeddable(entity.getMerchantCategoryModel()));
        }
        if(entity.getTransactionModel()!=null){
        	embeddables.addAll(transactionResourceAssembler.toEmbeddable(entity.getTransactionModel()));
        }
        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
