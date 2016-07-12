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
import com.dealmacha.businessdelegate.service.CashbackTransactionContext;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.model.CashbackTransactionModel;
import com.dealmacha.resources.assemblers.CashbackTransactionResourceAssembler;
import com.dealmacha.resources.hal.CashbackTransactionResource;

@RestController
@ExposesResourceFor(value = CashbackTransactionResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/cashbackTransaction", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class CashbackTransactionController {
    private static final Logger LOGGER = Logger.getLogger(CashbackTransactionController.class);

    private IBusinessDelegate<CashbackTransactionModel, CashbackTransactionContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<CashbackTransactionContext> cashbackTransactionContextFactory;

    @Autowired
    private CashbackTransactionResourceAssembler cashbackTransactionResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<CashbackTransactionResource> createCashbackTransaction(@RequestBody final CashbackTransactionModel cashbackTransactionModel) {
    	CashbackTransactionModel model = businessDelegate.create(cashbackTransactionModel);
    	CashbackTransactionResource resource = cashbackTransactionResourceAssembler.toResource(model);

        return new ResponseEntity<CashbackTransactionResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteCashbackTransaction(@PathVariable(value = "id") final String cashbackTransactionId) {

    	CashbackTransactionContext cashbackTransactionContext = cashbackTransactionContextFactory.getObject();
    	cashbackTransactionContext.setCashbackTransactionId(cashbackTransactionId);
        businessDelegate.delete(keyBuilderFactory.getObject().withId(cashbackTransactionId), cashbackTransactionContext);

    }
    @RequestMapping(method = RequestMethod.GET, value = "/users/{usersId}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<CashbackTransactionResource>> getUserCashbackTransaction(@PathVariable(value = "usersId") final String usersId) {
    	CashbackTransactionContext cashbackTransactionContext = cashbackTransactionContextFactory.getObject();
    	cashbackTransactionContext.setUsersId(usersId);
        /*    Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
            usersResourceAssembler.toResources(usersModels);*/
        Iterable<CashbackTransactionModel> cashbackTransactionModels = businessDelegate.getCollection(cashbackTransactionContext);
        final Iterable<CashbackTransactionResource> resources = cashbackTransactionResourceAssembler.toResources(cashbackTransactionModels);
        return new ResponseEntity<Iterable<CashbackTransactionResource>>(resources, HttpStatus.OK);
    }
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CashbackTransactionResource> edit(@PathVariable(value = "id") final String cashbackTransactionId, @RequestBody final CashbackTransactionModel cashbackTransactionModel) {
    	CashbackTransactionModel model=  businessDelegate.edit(keyBuilderFactory.getObject().withId(cashbackTransactionId), cashbackTransactionModel);
    	CashbackTransactionResource resource = cashbackTransactionResourceAssembler.toResource(model);
        return new ResponseEntity<CashbackTransactionResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<CashbackTransactionResource>> getAllCashbackTransaction() {
    	CashbackTransactionContext cashbackTransactionContext = cashbackTransactionContextFactory.getObject();
    	cashbackTransactionContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<CashbackTransactionModel> cashbackTransactionModels = businessDelegate.getCollection(cashbackTransactionContext);
        final Iterable<CashbackTransactionResource> resources = cashbackTransactionResourceAssembler.toResources(cashbackTransactionModels);
        return new ResponseEntity<Iterable<CashbackTransactionResource>>(resources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<CashbackTransactionModel> getCashbackTransaction(@PathVariable(value = "id") final String cashbackTransactionId) {
    	CashbackTransactionContext cashbackTransactionContext = cashbackTransactionContextFactory.getObject();
    	CashbackTransactionModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(cashbackTransactionId), cashbackTransactionContext);
        return new ResponseEntity<CashbackTransactionModel>(model, HttpStatus.OK);
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Resource(name = "cashbackTransactionBusinessDelegate")
    public void setCashbackTransactionBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setCashbackTransactionObjectFactory(final ObjectFactory<CashbackTransactionContext> cashbackTransactionContextFactory) {
        this.cashbackTransactionContextFactory = cashbackTransactionContextFactory;
    }

}
