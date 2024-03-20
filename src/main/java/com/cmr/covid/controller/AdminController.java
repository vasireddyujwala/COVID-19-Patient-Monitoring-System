package com.cmr.covid.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cmr.covid.model.DoctorPojo;
import com.cmr.covid.model.PatientPojo;
import com.cmr.covid.service.AdminService;

@Controller
@RequestMapping(value = { "/admin" })
public class AdminController {

	@Autowired
	AdminService adminService;

	@RequestMapping("/AdminProfile")
	public ModelAndView adminProfile(ModelAndView model, Authentication authentication) throws Exception {

		model.setViewName("AdminProfile");

		return model;
	}

	@RequestMapping("/adddoctor")
	public ModelAndView adddoctor(ModelAndView model, Authentication authentication,DoctorPojo doctorpojo) throws Exception {

		if(doctorpojo != null && doctorpojo.getdEmail() != null && !doctorpojo.getdEmail().isEmpty()) {
			String pwd = doctorpojo.getdPwd();
			doctorpojo.setdPwd("{noop}"+pwd);
			adminService.addDoctorInfo(doctorpojo);
		}
		
		model.setViewName("adddoctor");

		return model;
	}

	@RequestMapping("/dashboard")
	public ModelAndView admindashboard(ModelAndView model, Authentication authentication) throws Exception {

		Map<String, Integer> map = adminService.getAdminDashBoardDetails();

		model.addObject("ptcount", map.get("ptcount"));
		model.addObject("dtcount", map.get("dtcount"));
		model.addObject("ipcount", map.get("ipcount"));
		model.addObject("apcount", map.get("apcount"));
		model.setViewName("admindashboard");
		return model;
	}

	@RequestMapping("/ViewDoctors")
	public ModelAndView viewDoctors(ModelAndView model, Authentication authentication) throws Exception {

		List<DoctorPojo> listdoctorinfo = new ArrayList<>();
		List<DoctorPojo> listdoctorinfo1 = adminService.getDoctorsList();

		DoctorPojo doctorPojo = new DoctorPojo();
		model.addObject("doctorPojo", doctorPojo);
		
		for(DoctorPojo doctorPojo1 : listdoctorinfo1) {
			
			if(doctorPojo1.getDstatus().equalsIgnoreCase("0")) {
				doctorPojo1.setDstatus("In-Active");
			}else {
				doctorPojo1.setDstatus("Active");
			}
			
			listdoctorinfo.add(doctorPojo1);
			
		}
		model.addObject("listdoctorinfo", listdoctorinfo);
		model.setViewName("ViewDoctors");

		return model;
	}

	@RequestMapping("/ViewPatients")
	public ModelAndView ViewPatients(ModelAndView model, Authentication authentication) throws Exception {

		List<PatientPojo> listPatientsinfo = new ArrayList<>();
		
		List<PatientPojo> listPatientsinfo1 = adminService.getPatientsList();
		
		for(PatientPojo PatientPojo : listPatientsinfo1) {
			
			if(PatientPojo.getpStatus().equalsIgnoreCase("0")) {
				PatientPojo.setpStatus("In Active");
			}else {
				PatientPojo.setpStatus("Active");
			}
			listPatientsinfo.add(PatientPojo);
		}

		PatientPojo patientPojo = new PatientPojo();
		model.addObject("patientPojo", patientPojo);
		model.addObject("listPatientsinfo", listPatientsinfo);
		model.setViewName("ViewPatients");

		return model;
	}

}
