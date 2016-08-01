package com.dealmacha.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.dealmacha.businessdelegate.service.RequestTypesContext;
import com.dealmacha.model.RequestTypesModel;
import com.dealmacha.resources.assemblers.RequestTypesResourceAssembler;
import com.dealmacha.resources.hal.RequestTypesResource;

@RestController
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/admin/requestTypes", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class RequestTypesController {
    private static final Logger LOGGER = Logger.getLogger(RequestTypesController.class);

    private IBusinessDelegate<RequestTypesModel, RequestTypesContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<RequestTypesContext> requestTypesContextFactory;

    @Autowired
    private RequestTypesResourceAssembler requestTypesResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<RequestTypesResource> createRequestTypes(@RequestBody final RequestTypesModel requestTypesModel) {
    	RequestTypesModel model = businessDelegate.create(requestTypesModel);
    	RequestTypesResource resource = requestTypesResourceAssembler.toResource(model);

        return new ResponseEntity<RequestTypesResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteRequestTypes(@PathVariable(value = "id") final String requestTypesId) {

    	RequestTypesContext requestTypesContext = requestTypesContextFactory.getObject();
    	requestTypesContext.setRequestTypesId(requestTypesId);
        businessDelegate.delete(keyBuilderFactory.getObject().withId(requestTypesId), requestTypesContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RequestTypesResource> edit(@PathVariable(value = "id") final String requestTypesId,
            @RequestBody final RequestTypesModel requestTypesModel) {
        RequestTypesModel model=businessDelegate.edit(keyBuilderFactory.getObject().withId(requestTypesId), requestTypesModel);
       	RequestTypesResource resource = requestTypesResourceAssembler.toResource(model);
        return new ResponseEntity<RequestTypesResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<RequestTypesModel> getRequestTypes(@PathVariable(value = "id") final String requestTypesId) {
    	RequestTypesContext requestTypesContext = requestTypesContextFactory.getObject();
    	RequestTypesModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(requestTypesId), requestTypesContext);
        return new ResponseEntity<RequestTypesModel>(model, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<RequestTypesResource>> getAllRequestTypes() {
    	RequestTypesContext requestTypesContext = requestTypesContextFactory.getObject();
    	requestTypesContext.setAll("all");
         /*Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<RequestTypesModel> requestTypesModels = businessDelegate.getCollection(requestTypesContext);
        final Iterable<RequestTypesResource> resources = requestTypesResourceAssembler.toResources(requestTypesModels);
        return new ResponseEntity<Iterable<RequestTypesResource>>(resources, HttpStatus.OK);
    }

    @Autowired
    public void setRequestTypesObjectFactory(final ObjectFactory<RequestTypesContext> requestTypesContextFactory) {
        this.requestTypesContextFactory = requestTypesContextFactory;
    }

   
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Autowired
    @Qualifier("requestTypesBusinessDelegate")
    public void setRequestTypesBusinessDelegate(final IBusinessDelegate<RequestTypesModel, RequestTypesContext, IKeyBuilder<String>, String> businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

}
