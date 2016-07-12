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
import com.dealmacha.businessdelegate.service.AddressContext;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.businessdelegate.service.RequestCommentsContext;
import com.dealmacha.model.AddressModel;
import com.dealmacha.model.RequestCommentsModel;
import com.dealmacha.resources.assemblers.AddressResourceAssembler;
import com.dealmacha.resources.assemblers.RequestCommentsResourceAssembler;
import com.dealmacha.resources.hal.AddressResource;
import com.dealmacha.resources.hal.RequestCommentsResource;

@RestController
@ExposesResourceFor(value = RequestCommentsResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/requestComments", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class RequestCommentsController {
    private static final Logger LOGGER = Logger.getLogger(RequestCommentsController.class);

    private IBusinessDelegate<RequestCommentsModel, RequestCommentsContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<RequestCommentsContext> requestCommentsContextFactory;

    @Autowired
    private RequestCommentsResourceAssembler requestCommentsResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<RequestCommentsResource> createRequestComments(@RequestBody final RequestCommentsModel requestCommentsModel) {
    	RequestCommentsModel model = businessDelegate.create(requestCommentsModel);
    	RequestCommentsResource resource = requestCommentsResourceAssembler.toResource(model);

        return new ResponseEntity<RequestCommentsResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteRequestComments(@PathVariable(value = "id") final String requestCommentsId) {

    	RequestCommentsContext requestCommentsContext = requestCommentsContextFactory.getObject();
    	requestCommentsContext.setRequestCommentsId(requestCommentsId);
        businessDelegate.delete(keyBuilderFactory.getObject().withId(requestCommentsId), requestCommentsContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RequestCommentsResource> edit(@PathVariable(value = "id") final String requestCommentsId,
            @RequestBody final RequestCommentsModel requestCommentsModel) {
        RequestCommentsModel model=businessDelegate.edit(keyBuilderFactory.getObject().withId(requestCommentsId), requestCommentsModel);
       	RequestCommentsResource resource = requestCommentsResourceAssembler.toResource(model);
        return new ResponseEntity<RequestCommentsResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<RequestCommentsModel> getRequestComments(@PathVariable(value = "id") final String requestCommentsId) {
    	RequestCommentsContext requestCommentsContext = requestCommentsContextFactory.getObject();
    	RequestCommentsModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(requestCommentsId), requestCommentsContext);
        return new ResponseEntity<RequestCommentsModel>(model, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<RequestCommentsResource>> getAllRequestComments() {
    	RequestCommentsContext requestCommentsContext = requestCommentsContextFactory.getObject();
    	requestCommentsContext.setAll("all");
         /*Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<RequestCommentsModel> requestCommentsModels = businessDelegate.getCollection(requestCommentsContext);
        final Iterable<RequestCommentsResource> resources = requestCommentsResourceAssembler.toResources(requestCommentsModels);
        return new ResponseEntity<Iterable<RequestCommentsResource>>(resources, HttpStatus.OK);
    }

    @Autowired
    public void setRequestCommentsObjectFactory(final ObjectFactory<RequestCommentsContext> requestCommentsContextFactory) {
        this.requestCommentsContextFactory = requestCommentsContextFactory;
    }

   
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Resource(name = "requestCommentsBusinessDelegate")
    public void setRequestCommentsBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

}
