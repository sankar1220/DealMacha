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
import com.dealmacha.model.RolesModel;
import com.dealmacha.resources.assemblers.RolesResourceAssembler;
import com.dealmacha.resources.hal.RolesResource;

@RestController
@ExposesResourceFor(value = RolesResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/roles", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class RolesController {
    private static final Logger LOGGER = Logger.getLogger(RolesController.class);

    private IBusinessDelegate<RolesModel, RolesContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<RolesContext> rolesContextFactory;

    @Autowired
    private RolesResourceAssembler rolesResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<RolesResource> createRoles(@RequestBody final RolesModel rolesModel) {
    	RolesModel model = businessDelegate.create(rolesModel);
    	RolesResource resource = rolesResourceAssembler.toResource(model);

        return new ResponseEntity<RolesResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteRoles(@PathVariable(value = "id") final String rolesId) {

    	RolesContext rolesContext = rolesContextFactory.getObject();
    	/*rolesContext.setRolesId(rolesId);*/
        businessDelegate.delete(keyBuilderFactory.getObject().withId(rolesId), rolesContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RolesResource> edit(@PathVariable(value = "id") final String rolesId,
            @RequestBody final RolesModel rolesModel) {
        RolesModel model=businessDelegate.edit(keyBuilderFactory.getObject().withId(rolesId), rolesModel);
       	RolesResource resource = rolesResourceAssembler.toResource(model);
        return new ResponseEntity<RolesResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<RolesModel> getRoles(@PathVariable(value = "id") final String rolesId) {
    	RolesContext rolesContext = rolesContextFactory.getObject();
    	RolesModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(rolesId), rolesContext);
        return new ResponseEntity<RolesModel>(model, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<RolesResource>> getAllRoles() {
    	RolesContext rolesContext = rolesContextFactory.getObject();
    	rolesContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<RolesModel> rolesModels = businessDelegate.getCollection(rolesContext);
        final Iterable<RolesResource> resources = rolesResourceAssembler.toResources(rolesModels);
        return new ResponseEntity<Iterable<RolesResource>>(resources, HttpStatus.OK);
    }

    @Autowired
    public void setRolesObjectFactory(final ObjectFactory<RolesContext> rolesContextFactory) {
        this.rolesContextFactory = rolesContextFactory;
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Autowired
    @Qualifier("rolesBusinessDelegate")
    public void setRolesBusinessDelegate(final IBusinessDelegate<RolesModel, RolesContext, IKeyBuilder<String>, String> businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

}
