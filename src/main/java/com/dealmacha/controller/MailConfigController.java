package com.dealmacha.controller;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.SimpleIdKeyBuilder;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.businessdelegate.service.MailConfigContext;
import com.dealmacha.model.MailConfigModel;
import com.dealmacha.resources.assemblers.MailConfigResourceAssembler;
import com.dealmacha.resources.hal.MailConfigResource;

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
@ExposesResourceFor(value = MailConfigResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/mailConfig", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class MailConfigController {
    private static final Logger LOGGER = Logger.getLogger(MailConfigController.class);

    private IBusinessDelegate<MailConfigModel, MailConfigContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<MailConfigContext> mailConfigContextFactory;

    @Autowired
    private MailConfigResourceAssembler mailConfigResourceAssembler;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<MailConfigResource> createMailConfig(@RequestBody final MailConfigModel mailConfigModel) {
        MailConfigModel model = businessDelegate.create(mailConfigModel);
        MailConfigResource resource = mailConfigResourceAssembler.toResource(model);
        return new ResponseEntity<MailConfigResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteMailConfig(@PathVariable(value = "id") final String mailConfigId) {

        MailConfigContext mailConfigContext = mailConfigContextFactory.getObject();
        mailConfigContext.setMailConfigId(mailConfigId);
        businessDelegate.delete(keyBuilderFactory.getObject().withId(mailConfigId), mailConfigContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MailConfigResource> edit(@PathVariable(value = "id") final String mailConfigId,
            @RequestBody final MailConfigModel mailConfigModel) {
        MailConfigContext mailConfigContext = new MailConfigContext();
        MailConfigModel model= businessDelegate.edit(keyBuilderFactory.getObject().withId(mailConfigId), mailConfigModel);
        MailConfigResource resource = mailConfigResourceAssembler.toResource(model);
        return new ResponseEntity<MailConfigResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<MailConfigResource>> getAllMailConfig() {
        MailConfigContext mailConfigContext = mailConfigContextFactory.getObject();
        mailConfigContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<MailConfigModel> mailConfigModels = businessDelegate.getCollection(mailConfigContext);
        final Iterable<MailConfigResource> resources = mailConfigResourceAssembler.toResources(mailConfigModels);
        return new ResponseEntity<Iterable<MailConfigResource>>(resources, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<MailConfigModel> getMailConfig(@PathVariable(value = "id") final String mailConfigId) {
        MailConfigContext mailConfigContext = mailConfigContextFactory.getObject();
        MailConfigModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(mailConfigId), mailConfigContext);
        return new ResponseEntity<MailConfigModel>(model, HttpStatus.OK);
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Resource(name = "mailConfigBusinessDelegate")
    public void setMailConfigBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setMailConfigObjectFactory(final ObjectFactory<MailConfigContext> mailConfigContextFactory) {
        this.mailConfigContextFactory = mailConfigContextFactory;
    }

}
