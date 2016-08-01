package com.dealmacha.controller;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.SimpleIdKeyBuilder;
import com.dealmacha.businessdelegate.service.AddressContext;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.model.AddressModel;
import com.dealmacha.resources.assemblers.AddressResourceAssembler;
import com.dealmacha.resources.hal.AddressResource;

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
import org.springframework.web.bind.annotation.*;

@RestController
@ExposesResourceFor(value = AddressResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/address", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class AddressController {
    private static final Logger LOGGER = Logger.getLogger(AddressController.class);
    private IBusinessDelegate<AddressModel, AddressContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<AddressContext> addressContextFactory;

    @Autowired
    private AddressResourceAssembler addressResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<AddressResource> createAddress(@RequestBody final AddressModel addressModel) {
        AddressModel model = businessDelegate.create(addressModel);
        AddressResource resource = addressResourceAssembler.toResource(model);

        return new ResponseEntity<AddressResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteAddress(@PathVariable(value = "id") final String addressId) {

        AddressContext addressContext = addressContextFactory.getObject();
        addressContext.setAddressId(addressId);
        businessDelegate.delete(keyBuilderFactory.getObject().withId(addressId), addressContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AddressResource> edit(@PathVariable(value = "id") final String addressId,
            @RequestBody final AddressModel addressModel) {
    	AddressModel model= businessDelegate.edit(keyBuilderFactory.getObject().withId(addressId), addressModel);
        AddressResource resource = addressResourceAssembler.toResource(model);
        return new ResponseEntity<AddressResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<AddressModel> getAddress(@PathVariable(value = "id") final String addressId) {
        AddressContext addressContext = addressContextFactory.getObject();
        AddressModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(addressId), addressContext);
        return new ResponseEntity<AddressModel>(model, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<AddressResource>> getAllAddress() {
        AddressContext addressContext = addressContextFactory.getObject();
        addressContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<AddressModel> addressModels = businessDelegate.getCollection(addressContext);
        final Iterable<AddressResource> resources = addressResourceAssembler.toResources(addressModels);
        return new ResponseEntity<Iterable<AddressResource>>(resources, HttpStatus.OK);
    }

    @Autowired
    @Qualifier("addressBusinessDelegate")
    public void setAddressBusinessDelegate(final IBusinessDelegate<AddressModel, AddressContext, IKeyBuilder<String>, String> businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setAddressObjectFactory(final ObjectFactory<AddressContext> addressContextFactory) {
        this.addressContextFactory = addressContextFactory;
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

}
