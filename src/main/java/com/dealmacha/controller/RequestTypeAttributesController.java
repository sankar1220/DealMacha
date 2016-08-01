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
import com.dealmacha.businessdelegate.service.RequestTypeAttributesContext;
import com.dealmacha.model.RequestTypeAttributesModel;
import com.dealmacha.resources.assemblers.RequestTypeAttributesResourceAssembler;
import com.dealmacha.resources.hal.RequestTypeAttributesResource;

	@RestController
	@EnableHypermediaSupport(type = { HypermediaType.HAL })
	@RequestMapping(value = "/admin/requestTypeAttributes", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public class RequestTypeAttributesController {
	    private static final Logger LOGGER = Logger.getLogger(RequestTypeAttributesController.class);

	    private IBusinessDelegate<RequestTypeAttributesModel, RequestTypeAttributesContext, IKeyBuilder<String>, String> businessDelegate;
	    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
	    private ObjectFactory<RequestTypeAttributesContext> requestTypeAttributesContextFactory;

	    @Autowired
	    private RequestTypeAttributesResourceAssembler requestTypeAttributesResourceAssembler;

	    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
	    @ResponseBody
	    public ResponseEntity<RequestTypeAttributesResource> createRequestTypeAttributes(@RequestBody final RequestTypeAttributesModel requestTypeAttributesModel) {
	    	RequestTypeAttributesModel model = businessDelegate.create(requestTypeAttributesModel);
	    	RequestTypeAttributesResource resource = requestTypeAttributesResourceAssembler.toResource(model);

	        return new ResponseEntity<RequestTypeAttributesResource>(resource, HttpStatus.CREATED);
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
	    @ResponseBody
	    public void deleteRequestTypeAttributes(@PathVariable(value = "id") final String requestTypeAttributesId) {

	    	RequestTypeAttributesContext requestTypeAttributesContext = requestTypeAttributesContextFactory.getObject();
	    	requestTypeAttributesContext.setRequestTypeAttributesId(requestTypeAttributesId);
	        businessDelegate.delete(keyBuilderFactory.getObject().withId(requestTypeAttributesId), requestTypeAttributesContext);

	    }

	    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	    @ResponseBody
	    public ResponseEntity<RequestTypeAttributesResource> edit(@PathVariable(value = "id") final String requestTypeAttributesId,
	            @RequestBody final RequestTypeAttributesModel requestTypeAttributesModel) {
	        RequestTypeAttributesModel model=businessDelegate.edit(keyBuilderFactory.getObject().withId(requestTypeAttributesId), requestTypeAttributesModel);
	       	RequestTypeAttributesResource resource = requestTypeAttributesResourceAssembler.toResource(model);
	        return new ResponseEntity<RequestTypeAttributesResource>(resource, HttpStatus.CREATED);
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
	    @ResponseBody
	    public ResponseEntity<RequestTypeAttributesModel> getRequestTypeAttributes(@PathVariable(value = "id") final String requestTypeAttributesId) {
	    	RequestTypeAttributesContext requestTypeAttributesContext = requestTypeAttributesContextFactory.getObject();
	    	RequestTypeAttributesModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(requestTypeAttributesId), requestTypeAttributesContext);
	        return new ResponseEntity<RequestTypeAttributesModel>(model, HttpStatus.OK);
	    }

	    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
	    @ResponseBody
	    public ResponseEntity<Iterable<RequestTypeAttributesResource>> getAllRequestTypeAttributes() {
	    	RequestTypeAttributesContext requestTypeAttributesContext = requestTypeAttributesContextFactory.getObject();
	    	requestTypeAttributesContext.setAll("all");
	        Iterable<RequestTypeAttributesModel> requestTypeAttributesModels = businessDelegate.getCollection(requestTypeAttributesContext);
	        final Iterable<RequestTypeAttributesResource> resources = requestTypeAttributesResourceAssembler.toResources(requestTypeAttributesModels);
	        return new ResponseEntity<Iterable<RequestTypeAttributesResource>>(resources, HttpStatus.OK);
	    }

	    @Autowired
	    public void setRequestTypeAttributesObjectFactory(final ObjectFactory<RequestTypeAttributesContext> requestTypeAttributesContextFactory) {
	        this.requestTypeAttributesContextFactory = requestTypeAttributesContextFactory;
	    }

	   
	    @Resource
	    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
	        keyBuilderFactory = factory;
	    }

	    @Autowired
	    @Qualifier("requestTypeAttributesBusinessDelegate")
	    public void setRequestTypeAttributesBusinessDelegate(final IBusinessDelegate<RequestTypeAttributesModel, RequestTypeAttributesContext, IKeyBuilder<String>, String> businessDelegate) {
	        this.businessDelegate = businessDelegate;
	    }

}
