package com.dealmacha.controller;

import com.dealmacha.businessdelegate.domain.IKeyBuilder;
import com.dealmacha.businessdelegate.domain.SimpleIdKeyBuilder;
import com.dealmacha.businessdelegate.service.IBusinessDelegate;
import com.dealmacha.businessdelegate.service.UsersContext;
import com.dealmacha.model.UsersModel;
import com.dealmacha.resources.assemblers.UsersResourceAssembler;
import com.dealmacha.resources.hal.UsersResource;
import com.dealmacha.security.CustomUserDetails;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@ExposesResourceFor(value = UsersResource.class)
@EnableHypermediaSupport(type = { HypermediaType.HAL })
@RequestMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
public class UsersController {
    private static final Logger LOGGER = Logger.getLogger(UsersController.class);

    private IBusinessDelegate<UsersModel, UsersContext, IKeyBuilder<String>, String> businessDelegate;
    private ObjectFactory<SimpleIdKeyBuilder> keyBuilderFactory;
    private ObjectFactory<UsersContext> usersContextFactory;

    @Autowired
    private UsersResourceAssembler usersResourceAssembler;

    @RequestMapping(method = RequestMethod.POST, value = "/changePassword", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<UsersResource>> changePassword(@RequestBody final UsersModel userModel,
            final HttpSession session) /*throws ResourceNotFoundException */ {
        UsersContext usersContext = usersContextFactory.getObject();
        //String s = (String) session.getAttribute("userId");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        // System.out.println("userDetails" + userDetails.getId());
        if (userDetails.getId() != null) {
            usersContext.setUserId(userDetails.getId());
            usersContext.setChangePassword("YES");
            usersContext.setPassword(userModel.getPassword());
            usersContext.setNewPassword(userModel.getNewPassword());
            usersContext.setConfirmPassword(userModel.getConfirmPassword());
            Iterable<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
            final Iterable<UsersResource> resources = usersResourceAssembler.toResources(usersModels);
            return new ResponseEntity<Iterable<UsersResource>>(resources, HttpStatus.OK);
        }
        else {
            session.invalidate();
            // model.addAttribute("message", "Session Expired! Login Again");
            // return "redirect:" + url + "/login";
            Iterable<UsersResource> models = null;
            return new ResponseEntity<Iterable<UsersResource>>(models, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<UsersResource> createUsers(@RequestBody final UsersModel usersModel) {
        UsersModel model = businessDelegate.create(usersModel);
        UsersResource resource = usersResourceAssembler.toResource(model);

        return new ResponseEntity<UsersResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/delete", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public void deleteUsers(@PathVariable(value = "id") final String usersId) {

        UsersContext usersContext = usersContextFactory.getObject();
        /*rolesContext.setRolesId(rolesId);*/
        businessDelegate.delete(keyBuilderFactory.getObject().withId(usersId), usersContext);

    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UsersResource> edit(@PathVariable(value = "id") final String usersId, @RequestBody final UsersModel usersModel) {
    	UsersModel model=   businessDelegate.edit(keyBuilderFactory.getObject().withId(usersId), usersModel);
    	UsersResource resource=usersResourceAssembler.toResource(model);
        return new ResponseEntity<UsersResource>(resource, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<UsersResource>> getAllUsers() {
        UsersContext usersContext = usersContextFactory.getObject();
        usersContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        final Iterable<UsersResource> resources = usersResourceAssembler.toResources(usersModels);
        return new ResponseEntity<Iterable<UsersResource>>(resources, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/usersOnly", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<Iterable<UsersResource>> getAllActivatedUsers() {
        UsersContext usersContext = usersContextFactory.getObject();
         usersContext.setActivatedUsers("activated users");         
        usersContext.setAll("all");
        /* Collection<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        usersResourceAssembler.toResources(usersModels);*/
        Iterable<UsersModel> usersModels = businessDelegate.getCollection(usersContext);
        final Iterable<UsersResource> resources = usersResourceAssembler.toResources(usersModels);
        return new ResponseEntity<Iterable<UsersResource>>(resources, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = { MediaType.ALL_VALUE })
    @ResponseBody
    public ResponseEntity<UsersModel> getUsers(@PathVariable(value = "id") final String usersId) {
        UsersContext usersContext = usersContextFactory.getObject();
        UsersModel model = businessDelegate.getByKey(keyBuilderFactory.getObject().withId(usersId), usersContext);
        return new ResponseEntity<UsersModel>(model, HttpStatus.OK);
    }

    /**
     * @param factory
     */
    @Resource
    public void setKeyBuilderFactory(final ObjectFactory<SimpleIdKeyBuilder> factory) {
        keyBuilderFactory = factory;
    }

    @Resource(name = "usersBusinessDelegate")
    public void setUsersBusinessDelegate(final IBusinessDelegate businessDelegate) {
        this.businessDelegate = businessDelegate;
    }

    @Autowired
    public void setUsersObjectFactory(final ObjectFactory<UsersContext> usersContextFactory) {
        this.usersContextFactory = usersContextFactory;
    }

}
