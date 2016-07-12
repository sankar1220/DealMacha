/**
 *
 */
package com.dealmacha.controller;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.SimpleIdKeyBuilder;
import com.dealmacha.businessdelegate.service.CmsBlockContext;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.model.CmsBlockModel;
import com.dealmacha.resources.assemblers.CmsBlockResourceAssembler;
import com.dealmacha.resources.hal.CmsBlockResource;

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
 * @author Naveen-PC
 *
 */

@RestController
@ExposesResourceFor(value = CmsBlockResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/cmsBlock", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class CmsBlockController {

    @Autowired
    private CmsBlockResourceAssembler cmsBlockAssemblerResource;

    private IBusinessDelegate<CmsBlockModel, CmsBlockContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;

    private ObjectFactory<CmsBlockContext> cmsBlockObjectFactory;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    public ResponseEntity<CmsBlockResource> createBlock(@RequestBody final CmsBlockModel cmsBlockModel) {

        CmsBlockModel model = businessDelegate.create(cmsBlockModel);
        CmsBlockResource resource = cmsBlockAssemblerResource.toResource(model);
        return new ResponseEntity<CmsBlockResource>(resource, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/blockName/{blockName}/edit", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    public ResponseEntity<CmsBlockResource> editBlockByName(@PathVariable(value = "blockName") final String blockName,
            @RequestBody final CmsBlockModel cmsBlockModel) {

        CmsBlockModel model = businessDelegate.edit(keyBuilderFactory.getObject().withName(blockName), cmsBlockModel);
        CmsBlockResource resource = cmsBlockAssemblerResource.toResource(model);
        return new ResponseEntity<CmsBlockResource>(resource, HttpStatus.OK);
    }

    @Resource(name = "cmsBlockBusinessDelegate")
    public void setCmsBlockBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setCmsBlockObjectFactory(final ObjectFactory<CmsBlockContext> cmsBlockObjectFactory) {
        this.cmsBlockObjectFactory = cmsBlockObjectFactory;

    }

    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;

    }
}
