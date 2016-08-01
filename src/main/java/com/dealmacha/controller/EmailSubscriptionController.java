/**
 *
 */
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
import com.dealmacha.businessdelegate.service.EmailSubscriptionContext;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.model.EmailSubscriptionModel;
import com.dealmacha.resources.assemblers.EmailSubscriptionResourceAssembler;
import com.dealmacha.resources.hal.EmailSubscriptionResource;

/**
 * @author varma
 *
 */

@RestController
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/users/emailSubscription", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
		MediaType.APPLICATION_JSON_VALUE })
public class EmailSubscriptionController {
	private static final Logger LOGGER = Logger.getLogger(EmailSubscriptionController.class);
	private IBusinessDelegate<EmailSubscriptionModel, EmailSubscriptionContext, IKeyBuilder<String>, String> businessDelegate;
	private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
	private ObjectFactory<EmailSubscriptionContext> emailSubscriptionContextFactory;

	@Autowired
	private EmailSubscriptionResourceAssembler emailSubscriptionResourceAssembler;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
	@ResponseBody
	public ResponseEntity<EmailSubscriptionResource> createEmailSubscription(
			@RequestBody final EmailSubscriptionModel emailSubscriptionModel) {
		EmailSubscriptionModel model = businessDelegate.create(emailSubscriptionModel);
		EmailSubscriptionResource resource = emailSubscriptionResourceAssembler.toResource(model);
		return new ResponseEntity<EmailSubscriptionResource>(resource, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
	@ResponseBody
	public void deleteEmailSubscription(@PathVariable(value = "id") final String emailSubscriptionId) {

		EmailSubscriptionContext emailSubscriptionContext = emailSubscriptionContextFactory.getObject();
		emailSubscriptionContext.setEmailSubscriptionId(emailSubscriptionId);
		businessDelegate.delete(keyBuilderFactory.getObject().withId(emailSubscriptionId), emailSubscriptionContext);

	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<EmailSubscriptionResource> edit(@PathVariable(value = "id") final String emailSubscriptionId,
			@RequestBody final EmailSubscriptionModel emailSubscriptionModel) {
		EmailSubscriptionModel model = businessDelegate.edit(keyBuilderFactory.getObject().withId(emailSubscriptionId),
				emailSubscriptionModel);
		EmailSubscriptionResource resource = emailSubscriptionResourceAssembler.toResource(model);
		return new ResponseEntity<EmailSubscriptionResource>(resource, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/mailsToAllUsers", consumes = { MediaType.ALL_VALUE })
	@ResponseBody
	public ResponseEntity<Iterable<EmailSubscriptionResource>> getEmailSubscriptionsSendsMailsToUsers(
			@PathVariable(value = "id") final String emailSubscriptionId) {
		EmailSubscriptionContext emailSubscriptionContext = emailSubscriptionContextFactory.getObject();
		emailSubscriptionContext.setAllEmails("ALLEmails");
		Iterable<EmailSubscriptionModel> emailSubscriptionModels = businessDelegate
				.getCollection(emailSubscriptionContext);
		final Iterable<EmailSubscriptionResource> resources = emailSubscriptionResourceAssembler
				.toResources(emailSubscriptionModels);
		return new ResponseEntity<Iterable<EmailSubscriptionResource>>(resources, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
	@ResponseBody
	public ResponseEntity<Iterable<EmailSubscriptionResource>> getAllEmailSubscription() {
		EmailSubscriptionContext emailSubscriptionContext = emailSubscriptionContextFactory.getObject();
		emailSubscriptionContext.setAll("all");
		/*
		 * Collection<UsersModel> usersModels =
		 * businessDelegate.getCollection(usersContext);
		 * usersResourceAssembler.toResources(usersModels);
		 */
		Iterable<EmailSubscriptionModel> emailSubscriptionModels = businessDelegate
				.getCollection(emailSubscriptionContext);
		final Iterable<EmailSubscriptionResource> resources = emailSubscriptionResourceAssembler
				.toResources(emailSubscriptionModels);
		return new ResponseEntity<Iterable<EmailSubscriptionResource>>(resources, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
	@ResponseBody
	public ResponseEntity<EmailSubscriptionModel> getEmailSubscription(
			@PathVariable(value = "id") final String emailSubscriptionId) {
		EmailSubscriptionContext emailSubscriptionContext = emailSubscriptionContextFactory.getObject();
		EmailSubscriptionModel model = businessDelegate
				.getByKey(keyBuilderFactory.getObject().withId(emailSubscriptionId), emailSubscriptionContext);
		return new ResponseEntity<EmailSubscriptionModel>(model, HttpStatus.OK);
	}

	@Autowired
	@Qualifier("emailSubscriptionBusinessDelegate")
	public void setEmailSubscriptionBusinessDelegate(final IBusinessDelegate<EmailSubscriptionModel, EmailSubscriptionContext, IKeyBuilder<String>, String> businessDelegate) {
		this.businessDelegate = businessDelegate;
	}

	
	
	@Autowired
	public void setEmailSubscriptionObjectFactory(
			final ObjectFactory<EmailSubscriptionContext> emailSubscriptionContextFactory) {
		this.emailSubscriptionContextFactory = emailSubscriptionContextFactory;
	}

	/**
	 * @param factory
	 */
	@Resource
	public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
		keyBuilderFactory = factory;
	}

}
