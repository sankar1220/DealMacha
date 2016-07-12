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

import com.dealmacha.controller.AccountController;
import com.dealmacha.controller.HomeController;
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.HomeModel;
import com.dealmacha.resources.hal.AccountResource;
import com.dealmacha.resources.hal.HomeResource;

@Service
public class HomeResourceAssembler extends EmbeddableResourceAssemblerSupport<HomeModel, HomeResource, HomeController> {

    @Autowired
    public HomeResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, HomeController.class, HomeResource.class);
    }

    @Override
    public Link linkToSingleResource(final HomeModel homeModel) {
        return entityLinks.linkToSingleResource(HomeResource.class, homeModel.getId());
    }

    public HomeResource toDetailedResource(final HomeModel entity) {
        final HomeResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public HomeResource toResource(final HomeModel entity) {
        final HomeResource resource = createResourceWithId(entity.getActivatedUsers(), entity);
        BeanUtils.copyProperties(entity, resource);
        
   
    /*   if(entity.getActivatedUsers()!=null){*/
/*resource.setActivatedUsers(entity.getActivatedUsers());
resource.setCommissionReceived(entity.getCommissionReceived());
resource.setTotalTransactions(entity.getTotalTransactions());*/
    /*   }*/
        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();

        resource.setEmbeddeds(new Resources<>(embeddables));
        return resource;
    }

}
