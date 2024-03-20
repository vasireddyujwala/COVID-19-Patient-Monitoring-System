package com.cmr.covid.service;

import java.util.List;
import java.util.Map;

import com.cmr.covid.model.DoctorPojo;
import com.cmr.covid.model.PatientPojo;

public interface AdminService {
	
	Map<String,Integer> getAdminDashBoardDetails();
	
	List<PatientPojo> getPatientsList();
	
	List<DoctorPojo> getDoctorsList();
	
	int addDoctorInfo(DoctorPojo doctorpojo);

}
