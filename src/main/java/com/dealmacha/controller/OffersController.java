/**
 *
 */
package com.dealmacha.controller;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.SimpleIdKeyBuilder;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.businessdelegate.service.OffersContext;
import com.dealmacha.model.OffersModel;
import com.dealmacha.resources.assemblers.OffersResourceAssembler;
import com.dealmacha.resources.hal.OffersResource;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author arthvedi5
 *
 */
@RestController
@ExposesResourceFor(value = OffersResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/offers", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class OffersController {
    private static final Logger LOGGER = Logger.getLogger(OffersController.class);
    private IBusinessDelegate<OffersModel, OffersContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<OffersContext> offersContextFactory;

    @Autowired
    private OffersResourceAssembler offersResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<OffersResource> createOffers(@RequestBody final OffersModel offersModel) {
        OffersModel model = businessDelegate.create(offersModel);
        OffersResource resource = offersResourceAssembler.toResource(model);

        return new ResponseEntity<OffersResource>(resource, HttpStatus.CREATED);
    }

    /* @RequestMapping(method = RequestMethod.POST, value = "/{id}/offersFromPayoomOffer", consumes = { MediaType.ALL_VALUE })
     @ResponseBody
     public ResponseEntity<Iterable<OffersResource>> createOffersFromPayoomOffer(@RequestBody final OffersModel offersModel) {

         OffersContext offersContext = offersContextFactory.getObject();
         offersContext.setOffersFromPayoomOffer("offersFromPayoomOffer");

         Iterable<OffersModel> offersModels = businessDelegate.getCollection(offersContext);
         final Iterable<OffersResource> resources = offersResourceAssembler.toResources(offersModels);
         return new ResponseEntity<Iterable<OffersResource>>(resources, HttpStatus.OK);
     }*/

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteOffers(@PathVariable(value = "id") final String offersId) {

        OffersContext offersContext = offersContextFactory.getObject();
        offersContext.setOffersId(offersId);
        businessDelegate.delete(keyBuilderFactory.getObject().withId(offersId), offersContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<OffersResource> edit(@PathVariable(value = "id") final String offersId, @RequestBody final OffersModel offersModel) {
    	OffersModel model=  businessDelegate.edit(keyBuilderFactory.getObject().withId(offersId), offersModel);
        OffersResource resource = offersResourceAssembler.toResource(model);
        return new ResponseEntity<OffersResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<OffersResource>> getAllOffers() {
        OffersContext offersContext = offersContextFactory.getObject();
        offersContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<OffersModel> offersModels = businessDelegate.getCollection(offersContext);
        final Iterable<OffersResource> resources = offersResourceAssembler.toResources(offersModels);
        return new ResponseEntity<Iterable<OffersResource>>(resources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<OffersModel> getOffers(@PathVariable(value = "id") final String offersId) {
        OffersContext offersContext = offersContextFactory.getObject();
        OffersModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(offersId), offersContext);
        return new ResponseEntity<OffersModel>(model, HttpStatus.OK);
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Resource(name = "offersBusinessDelegate")
    public void setOffersBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setOffersObjectFactory(final ObjectFactory<OffersContext> offersContextFactory) {
        this.offersContextFactory = offersContextFactory;
    }

}
