package com.dealmacha.controller;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.SimpleIdKeyBuilder;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.businessdelegate.service.MerchantContext;
import com.dealmacha.model.MerchantModel;
import com.dealmacha.resources.assemblers.MerchantResourceAssembler;
import com.dealmacha.resources.hal.MerchantResource;

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

@RestController
@ExposesResourceFor(value = MerchantResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/merchant", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class MerchantController {
    private static final Logger LOGGER = Logger.getLogger(MerchantController.class);

    private IBusinessDelegate<MerchantModel, MerchantContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<MerchantContext> merchantContextFactory;

    @Autowired
    private MerchantResourceAssembler merchantResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<MerchantResource> createMerchant(@RequestBody final MerchantModel merchantModel) {
        MerchantModel model = businessDelegate.create(merchantModel);
        MerchantResource resource = merchantResourceAssembler.toResource(model);
        return new ResponseEntity<MerchantResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteMerchant(@PathVariable(value = "id") final String merchantId) {

        MerchantContext merchantContext = merchantContextFactory.getObject();
        merchantContext.setMerchantId(merchantId);
        businessDelegate.delete(keyBuilderFactory.getObject().withId(merchantId), merchantContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MerchantResource> edit(@PathVariable(value = "id") final String merchantId,
            @RequestBody final MerchantModel merchantModel) {
        MerchantContext merchantContext = new MerchantContext();
       MerchantModel model= businessDelegate.edit(keyBuilderFactory.getObject().withId(merchantId), merchantModel);
        MerchantResource resource = merchantResourceAssembler.toResource(model);
        return new ResponseEntity<MerchantResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<MerchantResource>> getAllMerchant() {
        MerchantContext merchantContext = merchantContextFactory.getObject();
        merchantContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<MerchantModel> merchantModels = businessDelegate.getCollection(merchantContext);
        final Iterable<MerchantResource> resources = merchantResourceAssembler.toResources(merchantModels);
        return new ResponseEntity<Iterable<MerchantResource>>(resources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<MerchantResource> getMerchant(@PathVariable(value = "id") final String merchantId) {
        MerchantContext merchantContext = merchantContextFactory.getObject();
        MerchantModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(merchantId), merchantContext);
        MerchantResource resource = merchantResourceAssembler.toResource(model);
        return new ResponseEntity<MerchantResource>(resource, HttpStatus.OK);
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Resource(name = "merchantBusinessDelegate")
    public void setMerchantBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setMerchantObjectFactory(final ObjectFactory<MerchantContext> merchantContextFactory) {
        this.merchantContextFactory = merchantContextFactory;
    }

}
