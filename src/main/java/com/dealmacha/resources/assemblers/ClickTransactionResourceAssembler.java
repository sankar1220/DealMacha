package com.dealmacha.resources.assemblers;


import com.dealmacha.controller.ClickTransactionController;
import com.dealmacha.model.ClickTransactionModel;
import com.dealmacha.resources.hal.ClickTransactionResource;

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
public class ClickTransactionResourceAssembler extends EmbeddableResourceAssemblerSupport<ClickTransactionModel, ClickTransactionResource, ClickTransactionController> {

    @Autowired
    public ClickTransactionResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, ClickTransactionController.class, ClickTransactionResource.class);
    }

    @Override
    public Link linkToSingleResource(final ClickTransactionModel clickTransactionModel) {
        return entityLinks.linkToSingleResource(ClickTransactionResource.class, clickTransactionModel.getId());
    }

    public ClickTransactionResource toDetailedResource(final ClickTransactionModel entity) {
        final ClickTransactionResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public ClickTransactionResource toResource(final ClickTransactionModel entity) {
        final ClickTransactionResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
      
        // Add (multiple) links to authored books
        /*resource.add(relProvider.);*/
        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();
        /* if(entity.getTransactionsModels()!=null){
           embeddables.addAll( transactionResourceAssembler.toEmbeddable(entity.getTransactionsModels()));
         }*/
        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }
    
    

}
