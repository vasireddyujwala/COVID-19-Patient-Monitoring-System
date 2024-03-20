package com.cmr.covid.service;

import java.util.List;

import com.cmr.covid.model.DoctorPojo;
import com.cmr.covid.model.PatientPojo;

public interface DoctorService {
	
	List<PatientPojo> getPatientsList(String padoctor);
	
	List<PatientPojo> getPatientsActiveList(String padoctor);

	DoctorPojo getDoctorProfile(String demail);
	
	int UpdateDoctorDetails(DoctorPojo doctorPojo);
	
	List<DoctorPojo> getDoctorList();
	
}
