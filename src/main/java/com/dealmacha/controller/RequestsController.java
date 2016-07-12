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
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.businessdelegate.service.RequestsContext;
import com.dealmacha.model.RequestsModel;
import com.dealmacha.resources.assemblers.RequestsResourceAssembler;
import com.dealmacha.resources.hal.RequestsResource;

@RestController
@ExposesResourceFor(value = RequestsResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/requests", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class RequestsController {
    private static final Logger LOGGER = Logger.getLogger(RequestsController.class);

    private IBusinessDelegate<RequestsModel, RequestsContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<RequestsContext> requestsContextFactory;

    @Autowired
    private RequestsResourceAssembler requestsResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<RequestsResource> createRequests(@RequestBody final RequestsModel requestsModel) {
    	RequestsModel model = businessDelegate.create(requestsModel);
    	RequestsResource resource = requestsResourceAssembler.toResource(model);

        return new ResponseEntity<RequestsResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteRequests(@PathVariable(value = "id") final String requestsId) {

    	RequestsContext requestsContext = requestsContextFactory.getObject();
    	/*requestsContext.setRequestsId(requestsId);*/
        businessDelegate.delete(keyBuilderFactory.getObject().withId(requestsId), requestsContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RequestsResource> edit(@PathVariable(value = "id") final String requestsId,
            @RequestBody final RequestsModel requestsModel) {
        RequestsModel model =businessDelegate.edit(keyBuilderFactory.getObject().withId(requestsId), requestsModel);
        RequestsResource resource = requestsResourceAssembler.toResource(model);
        return new ResponseEntity<RequestsResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<RequestsModel> getRequests(@PathVariable(value = "id") final String requestsId) {
    	RequestsContext requestsContext = requestsContextFactory.getObject();
    	RequestsModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(requestsId), requestsContext);
        return new ResponseEntity<RequestsModel>(model, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<RequestsResource>> getAllRequests() {
    	RequestsContext requestsContext = requestsContextFactory.getObject();
    	requestsContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<RequestsModel> requestsModels = businessDelegate.getCollection(requestsContext);
        final Iterable<RequestsResource> resources = requestsResourceAssembler.toResources(requestsModels);
        return new ResponseEntity<Iterable<RequestsResource>>(resources, HttpStatus.OK);
    }

    @Autowired
    public void setRequestsObjectFactory(final ObjectFactory<RequestsContext> requestsContextFactory) {
        this.requestsContextFactory = requestsContextFactory;
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Resource(name = "requestsBusinessDelegate")
    public void setRequestsBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

}
