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
import com.dealmacha.businessdelegate.service.AccountContext;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.businessdelegate.service.TransactionContext;
import com.dealmacha.model.AccountModel;
import com.dealmacha.model.TransactionModel;
import com.dealmacha.resources.assemblers.AccountResourceAssembler;
import com.dealmacha.resources.hal.AccountResource;
import com.dealmacha.resources.hal.TransactionResource;

@RestController
@ExposesResourceFor(value = AccountResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/account", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class AccountController {
    private static final Logger LOGGER = Logger.getLogger(AccountController.class);
    private IBusinessDelegate<AccountModel, AccountContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<AccountContext> accountContextFactory;

    @Autowired
    private AccountResourceAssembler accountResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<AccountResource> createAccount(@RequestBody final AccountModel accountModel) {
        AccountModel model = businessDelegate.create(accountModel);
        AccountResource resource = accountResourceAssembler.toResource(model);

        return new ResponseEntity<AccountResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteAccount(@PathVariable(value = "id") final String accountId) {

        AccountContext accountContext = accountContextFactory.getObject();
        accountContext.setAccountId(accountId);
        businessDelegate.delete(keyBuilderFactory.getObject().withId(accountId), accountContext);

    }
    @RequestMapping(method = RequestMethod.GET, value = "/users/{usersId}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<AccountResource>> getUserAccount(@PathVariable(value = "usersId") final String usersId) {
    	AccountContext accountContext = accountContextFactory.getObject();
    	accountContext.setUsersId(usersId);
        /*    Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
            usersResourceAssembler.toResources(usersModels);*/
        Iterable<AccountModel> accountModels = businessDelegate.getCollection(accountContext);
        final Iterable<AccountResource> resources = accountResourceAssembler.toResources(accountModels);
        return new ResponseEntity<Iterable<AccountResource>>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AccountResource> edit(@PathVariable(value = "id") final String accountId,
            @RequestBody final AccountModel accountModel) {
    	AccountModel model=  businessDelegate.edit(keyBuilderFactory.getObject().withId(accountId), accountModel);
    	AccountResource resource=accountResourceAssembler.toResource(model);
        return new ResponseEntity<AccountResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<AccountModel> getAccount(@PathVariable(value = "id") final String accountId) {
        AccountContext accountContext = accountContextFactory.getObject();
        AccountModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(accountId), accountContext);
        return new ResponseEntity<AccountModel>(model, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<AccountResource>> getAllAccount() {
        AccountContext accountContext = accountContextFactory.getObject();
        accountContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<AccountModel> accountModels = businessDelegate.getCollection(accountContext);
        final Iterable<AccountResource> resources = accountResourceAssembler.toResources(accountModels);
        return new ResponseEntity<Iterable<AccountResource>>(resources, HttpStatus.OK);
    }

    @Resource(name = "accountBusinessDelegate")
    public void setAccountBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setAccountObjectFactory(final ObjectFactory<AccountContext> accountContextFactory) {
        this.accountContextFactory = accountContextFactory;
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

}
