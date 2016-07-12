/**
 *
 */
package com.dealmacha.controller;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.SimpleIdKeyBuilder;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.businessdelegate.service.MerchantCategoryMarginContext;
import com.dealmacha.model.MerchantCategoryMarginModel;
import com.dealmacha.resources.assemblers.MerchantCategoryMarginResourceAssembler;
import com.dealmacha.resources.hal.MerchantCategoryMarginResource;

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
@ExposesResourceFor(value = MerchantCategoryMarginResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/merchantCategoryMargin", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class MerchantCategoryMarginController {
    private static final Logger LOGGER = Logger.getLogger(MerchantCategoryMarginController.class);

    private IBusinessDelegate<MerchantCategoryMarginModel, MerchantCategoryMarginContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<MerchantCategoryMarginContext> merchantCategoryMarginContextFactory;

    @Autowired
    private MerchantCategoryMarginResourceAssembler merchantCategoryMarginResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<MerchantCategoryMarginResource> createMerchantCategoryMargin(
            @RequestBody final MerchantCategoryMarginModel merchantCategoryMarginModel) {
        MerchantCategoryMarginModel model = businessDelegate.create(merchantCategoryMarginModel);
        MerchantCategoryMarginResource resource = merchantCategoryMarginResourceAssembler.toResource(model);

        return new ResponseEntity<MerchantCategoryMarginResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteMerchantCategoryMargin(@PathVariable(value = "id") final String merchantCategoryMarginId) {

        MerchantCategoryMarginContext merchantCategoryMarginContext = merchantCategoryMarginContextFactory.getObject();
        merchantCategoryMarginContext.setMerchantCategoryMarginId(merchantCategoryMarginId);
        businessDelegate.delete(keyBuilderFactory.getObject().withId(merchantCategoryMarginId), merchantCategoryMarginContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MerchantCategoryMarginResource> edit(@PathVariable(value = "id") final String merchantCategoryMarginId,
            @RequestBody final MerchantCategoryMarginModel merchantCategoryMarginModel) {
     MerchantCategoryMarginModel model=   businessDelegate.edit(keyBuilderFactory.getObject().withId(merchantCategoryMarginId), merchantCategoryMarginModel);
        MerchantCategoryMarginResource resource = merchantCategoryMarginResourceAssembler.toResource(model);
        return new ResponseEntity<MerchantCategoryMarginResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<MerchantCategoryMarginResource>> getAllMerchantCategoryMargin() {
        MerchantCategoryMarginContext merchantCategoryMarginContext = merchantCategoryMarginContextFactory.getObject();
        merchantCategoryMarginContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<MerchantCategoryMarginModel> merchantCategoryMarginModels = businessDelegate.getCollection(merchantCategoryMarginContext);
        final Iterable<MerchantCategoryMarginResource> resources = merchantCategoryMarginResourceAssembler
                .toResources(merchantCategoryMarginModels);
        return new ResponseEntity<Iterable<MerchantCategoryMarginResource>>(resources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<MerchantCategoryMarginModel> getMerchantCategoryMargin(
            @PathVariable(value = "id") final String merchantCategoryMarginId) {
        MerchantCategoryMarginContext merchantCategoryMarginContext = merchantCategoryMarginContextFactory.getObject();
        MerchantCategoryMarginModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(merchantCategoryMarginId),
                merchantCategoryMarginContext);
        return new ResponseEntity<MerchantCategoryMarginModel>(model, HttpStatus.OK);
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Resource(name = "merchantCategoryMarginBusinessDelegate")
    public void setMerchantCategoryMarginBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setMerchantCategoryMarginObjectFactory(
            final ObjectFactory<MerchantCategoryMarginContext> merchantCategoryMarginContextFactory) {
        this.merchantCategoryMarginContextFactory = merchantCategoryMarginContextFactory;
    }

}
