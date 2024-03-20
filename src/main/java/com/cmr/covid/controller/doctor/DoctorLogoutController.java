package com.cmr.covid.controller.doctor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/doctor" })
public class DoctorLogoutController {

	@RequestMapping("/logout2")
	public ModelAndView indexPage(ModelAndView model, Authentication authentication) throws Exception {

		model.setViewName("logout2");

		return model;
	}

}