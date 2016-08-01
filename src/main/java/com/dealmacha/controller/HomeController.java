package com.dealmacha.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.SimpleIdKeyBuilder;
import com.dealmacha.businessdelegate.service.HomeContext;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.businessdelegate.service.TransactionContext;
import com.dealmacha.model.HomeModel;
import com.dealmacha.model.TransactionModel;
import com.dealmacha.resources.assemblers.HomeResourceAssembler;
import com.dealmacha.resources.assemblers.TransactionResourceAssembler;
import com.dealmacha.resources.hal.HomeResource;
import com.dealmacha.resources.hal.TransactionResource;


@RestController
@ExposesResourceFor(value = HomeResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/home", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class HomeController {
    private static final Logger LOGGER = Logger.getLogger(HomeController.class);

    private IBusinessDelegate<HomeModel, HomeContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<HomeContext> homeContextFactory;

    @Autowired
    private HomeResourceAssembler homeResourceAssembler;

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<HomeResource>> getAllActivatedUsers() {
        HomeContext homeContext = homeContextFactory.getObject();
        homeContext.setDashboardData("dashboard data");
       /*  homeContext.setActivatedUsers("activated users");         
        homeContext.setAll("all");
        
        homeContext.setTotalTransactions("total transactions");*/
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<HomeModel> homeModels = businessDelegate.getCollection(homeContext);
        final Iterable<HomeResource> resources = homeResourceAssembler.toResources(homeModels);
        return new ResponseEntity<Iterable<HomeResource>>(resources, HttpStatus.OK);
    }
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Autowired
    @Qualifier("homeBusinessDelegate")
    public void setHomeBusinessDelegate(final IBusinessDelegate<HomeModel, HomeContext, IKeyBuilder<String>, String> businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setHomeObjectFactory(final ObjectFactory<HomeContext> homeContextFactory) {
        this.homeContextFactory = homeContextFactory;
    }

}
