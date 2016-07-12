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

import com.dealmacha.controller.CashbackTransactionController;
import com.dealmacha.model.CashbackTransactionModel;
import com.dealmacha.resources.hal.CashbackTransactionResource;

@Service
public class CashbackTransactionResourceAssembler extends EmbeddableResourceAssemblerSupport<CashbackTransactionModel, CashbackTransactionResource, CashbackTransactionController> {

    @Autowired
    public CashbackTransactionResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, CashbackTransactionController.class, CashbackTransactionResource.class);
    }

    @Override
    public Link linkToSingleResource(final CashbackTransactionModel cashbackTransactionModel) {
        return entityLinks.linkToSingleResource(CashbackTransactionResource.class, cashbackTransactionModel.getId());
    }

    public CashbackTransactionResource toDetailedResource(final CashbackTransactionModel entity) {
        final CashbackTransactionResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public CashbackTransactionResource toResource(final CashbackTransactionModel entity) {
        final CashbackTransactionResource resource = createResourceWithId(entity.getId(), entity);
        BeanUtils.copyProperties(entity, resource);
        resource.setCashbackTransactionId(entity.getId());
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
