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
import com.dealmacha.businessdelegate.service.PayoomOfferContext;
import com.dealmacha.model.PayoomOfferModel;
import com.dealmacha.resources.assemblers.PayoomOfferResourceAssembler;
import com.dealmacha.resources.hal.PayoomOfferResource;

/**
 * @author mohan
 *
 */
@RestController
@ExposesResourceFor(value = PayoomOfferResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/payoomOffer", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class PayoomOfferController {
    private static final Logger LOGGER = Logger.getLogger(PayoomOfferController.class);
    private IBusinessDelegate<PayoomOfferModel, PayoomOfferContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<PayoomOfferContext> payoomOfferContextFactory;
    @Autowired
    private PayoomOfferResourceAssembler payoomOfferResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<PayoomOfferResource> createPayoomOffer(@RequestBody final PayoomOfferModel payoomOfferModel) {
        PayoomOfferModel model = businessDelegate.create(payoomOfferModel);
        PayoomOfferResource resource = payoomOfferResourceAssembler.toResource(model);
        return new ResponseEntity<PayoomOfferResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deletePayoomOffer(@PathVariable(value = "id") final String payoomOfferId) {
        PayoomOfferContext payoomOfferContext = payoomOfferContextFactory.getObject();
        businessDelegate.delete(keyBuilderFactory.getObject().withId(payoomOfferId), payoomOfferContext);
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PayoomOfferResource> edit(@PathVariable(value = "id") final String payoomOfferId,
            @RequestBody final PayoomOfferModel payoomOfferModel) {
       PayoomOfferModel model= businessDelegate.edit(keyBuilderFactory.getObject().withId(payoomOfferId), payoomOfferModel);
       PayoomOfferResource resource = payoomOfferResourceAssembler.toResource(model);
        return new ResponseEntity<PayoomOfferResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<PayoomOfferResource>> getAllPayoomOffer() {
        PayoomOfferContext payoomOfferContext = payoomOfferContextFactory.getObject();
        payoomOfferContext.setAll("all");
        Iterable<PayoomOfferModel> payoomOfferModels = businessDelegate.getCollection(payoomOfferContext);
        final Iterable<PayoomOfferResource> resources = payoomOfferResourceAssembler.toResources(payoomOfferModels);
        return new ResponseEntity<Iterable<PayoomOfferResource>>(resources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<PayoomOfferModel> getPayoomOffer(@PathVariable(value = "id") final String payoomOfferId) {
        PayoomOfferContext payoomOfferContext = payoomOfferContextFactory.getObject();
        PayoomOfferModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(payoomOfferId), payoomOfferContext);
        return new ResponseEntity<PayoomOfferModel>(model, HttpStatus.OK);
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Autowired
    @Qualifier("payoomOfferBusinessDelegate")
    public void setPayoomOfferBusinessDelegate(final IBusinessDelegate<PayoomOfferModel, PayoomOfferContext, IKeyBuilder<String>, String> businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setPayoomOfferObjectFactory(final ObjectFactory<PayoomOfferContext> payoomOfferContextFactory) {
        this.payoomOfferContextFactory = payoomOfferContextFactory;
    }

}