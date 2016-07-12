package com.dealmacha.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.SimpleIdKeyBuilder;
import com.dealmacha.businessdelegate.service.ClickTransactionContext;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.model.ClickTransactionModel;
import com.dealmacha.resources.assemblers.ClickTransactionResourceAssembler;
import com.dealmacha.resources.hal.ClickTransactionResource;

@RestController
@ExposesResourceFor(value = ClickTransactionResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/clickTransaction", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class ClickTransactionController {
    private static final Logger LOGGER = Logger.getLogger(ClickTransactionController.class);

    private IBusinessDelegate<ClickTransactionModel, ClickTransactionContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<ClickTransactionContext> clickTransactionContextFactory;

    @Autowired
    private ClickTransactionResourceAssembler clickTransactionResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<ClickTransactionResource> createClickTransaction(@RequestBody final ClickTransactionModel clickTransactionModel) {
    	ClickTransactionModel model = businessDelegate.create(clickTransactionModel);
    	ClickTransactionResource resource = clickTransactionResourceAssembler.toResource(model);

        return new ResponseEntity<ClickTransactionResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteClickTransaction(@PathVariable(value = "id") final String clickTransactionId) {

    	ClickTransactionContext clickTransactionContext = clickTransactionContextFactory.getObject();
    	clickTransactionContext.setClickTransactionId(clickTransactionId);
        businessDelegate.delete(keyBuilderFactory.getObject().withId(clickTransactionId), clickTransactionContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ClickTransactionResource> edit(@PathVariable(value = "id") final String clickTransactionId, @RequestBody final ClickTransactionModel clickTransactionModel) {
    	ClickTransactionModel model= businessDelegate.edit(keyBuilderFactory.getObject().withId(clickTransactionId), clickTransactionModel);
     	ClickTransactionResource resource = clickTransactionResourceAssembler.toResource(model);
        return new ResponseEntity<ClickTransactionResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<ClickTransactionResource>> getAllClickTransaction() {
    	ClickTransactionContext clickTransactionContext = clickTransactionContextFactory.getObject();
    	clickTransactionContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<ClickTransactionModel> clickTransactionModels = businessDelegate.getCollection(clickTransactionContext);
        final Iterable<ClickTransactionResource> resources = clickTransactionResourceAssembler.toResources(clickTransactionModels);
        return new ResponseEntity<Iterable<ClickTransactionResource>>(resources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<ClickTransactionModel> getClickTransaction(@PathVariable(value = "id") final String clickTransactionId) {
    	ClickTransactionContext clickTransactionContext = clickTransactionContextFactory.getObject();
    	ClickTransactionModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(clickTransactionId), clickTransactionContext);
        return new ResponseEntity<ClickTransactionModel>(model, HttpStatus.OK);
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Resource(name = "clickTransactionBusinessDelegate")
    public void setClickTransactionBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setClickTransactionObjectFactory(final ObjectFactory<ClickTransactionContext> clickTransactionContextFactory) {
        this.clickTransactionContextFactory = clickTransactionContextFactory;
    }

}
