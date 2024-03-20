package com.cmr.covid.service;

import com.cmr.covid.model.PatientPojo;

public interface PatientService {
	
	PatientPojo getPatientProfile(String pemail);
	
	int updatePatientDetails(PatientPojo patientPojo);
	
	int updatePatientProfileDetails(PatientPojo patientPojo);
	
}
