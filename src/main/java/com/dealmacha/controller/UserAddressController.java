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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.SimpleIdKeyBuilder;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.businessdelegate.service.RolesContext;
import com.dealmacha.businessdelegate.service.UserAddressContext;
import com.dealmacha.model.RolesModel;
import com.dealmacha.model.UserAddressModel;
import com.dealmacha.resources.assemblers.RolesResourceAssembler;
import com.dealmacha.resources.assemblers.UserAddressResourceAssembler;
import com.dealmacha.resources.hal.RolesResource;
import com.dealmacha.resources.hal.UserAddressResource;

@RestController
@ExposesResourceFor(value = UserAddressResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/userAddress", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class UserAddressController {
    private static final Logger LOGGER = Logger.getLogger(UserAddressController.class);

    private IBusinessDelegate<UserAddressModel, UserAddressContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<UserAddressContext> userAddressContextFactory;

    @Autowired
    private UserAddressResourceAssembler userAddressResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<UserAddressResource> createUserAddress(@RequestBody final UserAddressModel userAddressModel) {
    	UserAddressModel model = businessDelegate.create(userAddressModel);
    	UserAddressResource resource = userAddressResourceAssembler.toResource(model);

        return new ResponseEntity<UserAddressResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteUserAddress(@PathVariable(value = "id") final String userAddressId) {

    	UserAddressContext userAddressContext = userAddressContextFactory.getObject();
    	/*rolesContext.setRolesId(rolesId);*/
        businessDelegate.delete(keyBuilderFactory.getObject().withId(userAddressId), userAddressContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserAddressResource> edit(@PathVariable(value = "id") final String userAddressId,
            @RequestBody final UserAddressModel userAddressModel) {
     UserAddressModel model=   businessDelegate.edit(keyBuilderFactory.getObject().withId(userAddressId), userAddressModel);
     UserAddressResource resource = userAddressResourceAssembler.toResource(model);
        return new ResponseEntity<UserAddressResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<UserAddressModel> getUserAddress(@PathVariable(value = "id") final String userAddressId) {
    	UserAddressContext userAddressContext = userAddressContextFactory.getObject();
    	UserAddressModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(userAddressId), userAddressContext);
        return new ResponseEntity<UserAddressModel>(model, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<UserAddressResource>> getAllUserAddress() {
    	UserAddressContext userAddressContext = userAddressContextFactory.getObject();
    	userAddressContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<UserAddressModel> userAddressModels = businessDelegate.getCollection(userAddressContext);
        final Iterable<UserAddressResource> resources = userAddressResourceAssembler.toResources(userAddressModels);
        return new ResponseEntity<Iterable<UserAddressResource>>(resources, HttpStatus.OK);
    }

    @Autowired
    public void setUserAddressObjectFactory(final ObjectFactory<UserAddressContext> userAddressContextFactory) {
        this.userAddressContextFactory = userAddressContextFactory;
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Autowired
    @Qualifier("userAddressBusinessDelegate")
    public void setUserAddressBusinessDelegate(final IBusinessDelegate<UserAddressModel, UserAddressContext, IKeyBuilder<String>, String> businessDelegate) {
        this.businessDelegate = businessDelegate;
    }


}
