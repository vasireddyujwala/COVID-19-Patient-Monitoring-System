package com.cmr.covid.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cmr.covid.mail.Mail;
import com.cmr.covid.mail.MailService;
import com.cmr.covid.model.PatientPojo;
import com.cmr.covid.service.DoctorService;
import com.cmr.covid.service.PatientService;

@Controller
@RequestMapping(value = { "/user" })
public class PatientController {

	@Autowired
	PatientService patientService;

	@Autowired
	DoctorService doctorService;

	@Autowired
	MailService mailService;

	@RequestMapping(value = "/profile")
	public ModelAndView indexPage(ModelAndView model, Authentication authentication, PatientPojo patientPojoView) {

		PatientPojo patientPojo = patientService.getPatientProfile(authentication.getName());
		model.addObject("pName", patientPojo.getpName());
		model.addObject("pphno", patientPojo.getPphno());
		model.addObject("padoctor", doctorService.getDoctorProfile(patientPojo.getPadoctor()).getdName());
		model.addObject("pAddress", patientPojo.getpAddress());
		model.addObject("pEmail", patientPojo.getpEmail());

		if (patientPojoView != null) {

			if (patientPojoView.getpName() != null && !patientPojoView.getpName().isEmpty()) {
				patientPojoView.setpEmail(authentication.getName());
				patientService.updatePatientProfileDetails(patientPojoView);

				PatientPojo patientPojo1 = patientService.getPatientProfile(authentication.getName());
				model.addObject("pName", patientPojo1.getpName());
				model.addObject("pphno", patientPojo1.getPphno());
				model.addObject("padoctor", doctorService.getDoctorProfile(patientPojo.getPadoctor()).getdName());
				model.addObject("pAddress", patientPojo1.getpAddress());
				model.addObject("pEmail", patientPojo1.getpEmail());
			}
		}

		model.setViewName("profile");
		return model;
	}

	// User DashBoard Instailization

	@RequestMapping(value = "/dashboard")
	public ModelAndView patientdashboard(ModelAndView model, Authentication authentication, PatientPojo patientPojoView)
			throws MessagingException {

		PatientPojo patientPojo = patientService.getPatientProfile(authentication.getName());
		model.addObject("pName", patientPojo.getpName());
		model.addObject("pEmail", patientPojo.getpEmail());
		model.addObject("pbp", patientPojo.getpBp());
		model.addObject("ptemp", patientPojo.getpTemp());
		model.addObject("ppr", patientPojo.getpPr());

		if (patientPojoView != null) {

			if (patientPojoView.getpEmail() != null && !patientPojoView.getpEmail().isEmpty()) {

				int temp = Integer.valueOf(patientPojoView.getpTemp());
				int bp = Integer.valueOf(patientPojoView.getpBp());
				int pr = Integer.valueOf(patientPojoView.getpPr());
				if (temp > 100 || bp > 140 || pr > 100) {
					Mail mail = new Mail();
					mail.setTo(patientPojo.getPadoctor());
					mail.setSubject("Patient " + patientPojo.getpName() + "Condition is AbNormal ");
					mail.setToken("Paitent Condition is Abnormal found Abnormals values for Tempature :"
							+ patientPojoView.getpTemp() + "Blood Pressure :" + patientPojoView.getpBp()
							+ "Pluse Rate :" + patientPojoView.getpPr());
					mail.setFrom("vyshnavisindhe12@gmail.com");

					// mailService.sendEmail(mail);

					patientPojoView.setpStatus("1");
					patientService.updatePatientDetails(patientPojoView);
				} else {
					patientPojoView.setpStatus("0");
					patientService.updatePatientDetails(patientPojoView);
				}

				PatientPojo patientPojoupdated = patientService.getPatientProfile(authentication.getName());
				model.addObject("pName", patientPojoupdated.getpName());
				model.addObject("pEmail", patientPojoupdated.getpEmail());
				model.addObject("pbp", patientPojoupdated.getpBp());
				model.addObject("ptemp", patientPojoupdated.getpTemp());
				model.addObject("ppr", patientPojoupdated.getpPr());
			}

		}

		model.setViewName("patientdashboard");
		return model;
	}

}
