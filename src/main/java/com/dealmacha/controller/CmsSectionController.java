/**
 *
 */
package com.dealmacha.controller;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.SimpleIdKeyBuilder;
import com.dealmacha.businessdelegate.service.CmsSectionContext;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.model.CmsSectionModel;
import com.dealmacha.resources.assemblers.CmsSectionResourceAssembler;
import com.dealmacha.resources.hal.CmsSectionResource;

import javax.annotation.Resource;

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
 * @author Jay
 *
 */

@RestController
@ExposesResourceFor(value = CmsSectionResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/cmsSection", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class CmsSectionController {

    private IBusinessDelegate<CmsSectionModel, CmsSectionContext, IKeyBuilder<String>, String> businessDelegate;

    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<CmsSectionContext> cmsSectionObjectFactory;

    @Autowired
    private CmsSectionResourceAssembler cmsSectionResourceAssembler;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    public ResponseEntity<CmsSectionResource> createSection(@RequestBody final CmsSectionModel cmsSectionModel) {

        CmsSectionModel model = businessDelegate.create(cmsSectionModel);
        CmsSectionResource resource = cmsSectionResourceAssembler.toResource(model);

        return new ResponseEntity<CmsSectionResource>(resource, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/sectionName/{sectionName}/edit", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    public ResponseEntity<CmsSectionResource> editSectionByName(@PathVariable(value = "sectionName") final String sectionName,
            @RequestBody final CmsSectionModel cmsSectionModel) {

        CmsSectionModel model = businessDelegate.edit(keyBuilderFactory.getObject().withName(sectionName), cmsSectionModel);
        CmsSectionResource resource = cmsSectionResourceAssembler.toResource(model);
        return new ResponseEntity<CmsSectionResource>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/sectionName/{sectionName}", method = RequestMethod.GET, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<CmsSectionResource> getSectionByName(@PathVariable(value = "sectionName") final String sectionName) {

        CmsSectionContext cmsSectionContext = cmsSectionObjectFactory.getObject();
        cmsSectionContext.setSectionName(sectionName);
        CmsSectionModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(sectionName), cmsSectionContext);
        CmsSectionResource resource = cmsSectionResourceAssembler.toResource(model);
        return new ResponseEntity<CmsSectionResource>(resource, HttpStatus.OK);

    }

    @Resource(name = "cmsSectionBusinessDelegate")
    public void setCmsSectionBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setCmsSectionObjectFactory(final ObjectFactory<CmsSectionContext> cmsSectionObjectFactory) {
        this.cmsSectionObjectFactory = cmsSectionObjectFactory;
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

}
