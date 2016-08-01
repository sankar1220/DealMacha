/**
 *
 */
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
import com.dealmacha.businessdelegate.service.MerchantCategoryContext;
import com.dealmacha.model.MerchantCategoryModel;
import com.dealmacha.resources.assemblers.MerchantCategoryResourceAssembler;
import com.dealmacha.resources.hal.MerchantCategoryResource;

/**
 * @author arthvedi5
 *
 */
@RestController
@ExposesResourceFor(value = MerchantCategoryResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/merchantCategory", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class MerchantCategoryController {
    private static final Logger LOGGER = Logger.getLogger(MerchantCategoryController.class);

    private IBusinessDelegate<MerchantCategoryModel, MerchantCategoryContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<MerchantCategoryContext> merchantCategoryContextFactory;

    @Autowired
    private MerchantCategoryResourceAssembler merchantCategoryResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<MerchantCategoryResource> createMerchantCategory(@RequestBody final MerchantCategoryModel merchantCategoryModel) {
        MerchantCategoryModel model = businessDelegate.create(merchantCategoryModel);
        MerchantCategoryResource resource = merchantCategoryResourceAssembler.toResource(model);
        return new ResponseEntity<MerchantCategoryResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteMerchantCategory(@PathVariable(value = "id") final String merchantCategoryId) {

        MerchantCategoryContext merchantCategoryContext = merchantCategoryContextFactory.getObject();
        merchantCategoryContext.setMerchantCategoryId(merchantCategoryId);
        businessDelegate.delete(keyBuilderFactory.getObject().withId(merchantCategoryId), merchantCategoryContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MerchantCategoryResource> edit(@PathVariable(value = "id") final String merchantCategoryId,
            @RequestBody final MerchantCategoryModel merchantCategoryModel) {
        MerchantCategoryContext merchantCategoryContext = new MerchantCategoryContext();
        MerchantCategoryModel model= businessDelegate.edit(keyBuilderFactory.getObject().withId(merchantCategoryId), merchantCategoryModel);
        MerchantCategoryResource resource = merchantCategoryResourceAssembler.toResource(model);
        return new ResponseEntity<MerchantCategoryResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<MerchantCategoryResource>> getAllMerchantCategory() {
        MerchantCategoryContext merchantCategoryContext = merchantCategoryContextFactory.getObject();
        merchantCategoryContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<MerchantCategoryModel> merchantCategoryModels = businessDelegate.getCollection(merchantCategoryContext);
        final Iterable<MerchantCategoryResource> resources = merchantCategoryResourceAssembler.toResources(merchantCategoryModels);
        return new ResponseEntity<Iterable<MerchantCategoryResource>>(resources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<MerchantCategoryModel> getMerchantCategory(@PathVariable(value = "id") final String merchantCategoryId) {
        MerchantCategoryContext merchantCategoryContext = merchantCategoryContextFactory.getObject();
        MerchantCategoryModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(merchantCategoryId),
                merchantCategoryContext);
        return new ResponseEntity<MerchantCategoryModel>(model, HttpStatus.OK);
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Autowired
    @Qualifier("merchantCategoryBusinessDelegate")
    public void setMerchantCategoryBusinessDelegate(final IBusinessDelegate<MerchantCategoryModel, MerchantCategoryContext, IKeyBuilder<String>, String> businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setMerchantCategoryObjectFactory(final ObjectFactory<MerchantCategoryContext> merchantCategoryContextFactory) {
        this.merchantCategoryContextFactory = merchantCategoryContextFactory;
    }

}
