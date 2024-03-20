package com.cmr.covid.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cmr.covid.model.DoctorPojo;
import com.cmr.covid.model.PatientPojo;
import com.cmr.covid.service.DoctorService;

@Controller
@RequestMapping(value = { "/doctor" })
public class DoctorController {

	@Autowired
	DoctorService doctorService;

	@RequestMapping("/dashboard")
	public ModelAndView doctordashboard(ModelAndView model, Authentication authentication) throws Exception {

		List<PatientPojo> listPatientsinfo = new ArrayList<>();

		List<PatientPojo> listPatientsinfo1 = doctorService.getPatientsList(authentication.getName());

		for (PatientPojo PatientPojo : listPatientsinfo1) {

			if (PatientPojo.getpStatus().equalsIgnoreCase("0")) {
				PatientPojo.setpStatus("In Active");
			} else {
				PatientPojo.setpStatus("Active");
			}
			listPatientsinfo.add(PatientPojo);
		}

		PatientPojo patientPojo = new PatientPojo();
		model.addObject("patientPojo", patientPojo);
		model.addObject("listPatientsinfo", listPatientsinfo);
		model.setViewName("doctordashboard");

		return model;
	}
	
	
	@RequestMapping("/notification")
	public ModelAndView doctorNotifications(ModelAndView model, Authentication authentication) throws Exception {

		List<PatientPojo> listPatientsinfo = new ArrayList<>();

		List<PatientPojo> listPatientsinfo1 = doctorService.getPatientsActiveList(authentication.getName());

		for (PatientPojo PatientPojo : listPatientsinfo1) {

			if (PatientPojo.getpStatus().equalsIgnoreCase("0")) {
				PatientPojo.setpStatus("In Active");
			} else {
				PatientPojo.setpStatus("Active");
			}
			listPatientsinfo.add(PatientPojo);
		}

		PatientPojo patientPojo = new PatientPojo();
		model.addObject("patientPojo", patientPojo);
		model.addObject("listPatientsinfo", listPatientsinfo);
		model.setViewName("notification");

		return model;
	}

	@RequestMapping("/DoctorProfile")
	public ModelAndView doctorProfile(ModelAndView model, Authentication authentication, DoctorPojo doctorPojoView)
			throws Exception {

		DoctorPojo doctorPojo = doctorService.getDoctorProfile(authentication.getName());
		model.addObject("dName", doctorPojo.getdName());
		model.addObject("dPh", doctorPojo.getdPh());
		model.addObject("daddress", doctorPojo.getDaddress());
		model.addObject("dDesc", doctorPojo.getdDesc());
		model.addObject("dEmail", doctorPojo.getdEmail());

		if (doctorPojoView != null) {

			if (doctorPojoView.getdName() != null && !doctorPojoView.getdName().isEmpty()) {

				doctorPojoView.setdEmail(authentication.getName());
				doctorService.UpdateDoctorDetails(doctorPojoView);

				DoctorPojo doctorPojo1 = doctorService.getDoctorProfile(authentication.getName());
				model.addObject("dName", doctorPojo1.getdName());
				model.addObject("dPh", doctorPojo1.getdPh());
				model.addObject("daddress", doctorPojo1.getDaddress());
				model.addObject("dDesc", doctorPojo1.getdDesc());
				model.addObject("dEmail", doctorPojo1.getdEmail());

			}

		}
		
		model.setViewName("DoctorProfile");

		return model;
	}

}
