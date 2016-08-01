/*package com.dealmacha.controller.formdata;

import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dealmacha.constants.ThymeleafHtmlPageNames;

@Controller
@PropertySource("classpath:application.properties")
@ImportResource("classpath:spring-thymeleaf.xml")
public class UsersRegistrationController implements ThymeleafHtmlPageNames {

	@RequestMapping(value = "/register/users", method = RequestMethod.POST)
	public String usersRegisterByFormData(final Model model, @RequestParam("userName") final String userName,
			@RequestParam("emailId") final String emailId, @RequestParam("mobileNo") final String mobileNo,
			@RequestParam("password") final String password,
			@RequestParam("confirmPassword") final String confirmPassword) {

		
		return INDEX_PAGE;

	}
}*/
