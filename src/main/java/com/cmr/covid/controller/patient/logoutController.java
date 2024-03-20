package com.cmr.covid.controller.patient;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/user" })
public class logoutController {


	// User DashBoard Instailization

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView indexPage(ModelAndView model) {

		model.setViewName("logout");
		return model;
	}


}
