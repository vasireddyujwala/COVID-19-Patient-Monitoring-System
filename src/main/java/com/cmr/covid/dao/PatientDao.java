package com.cmr.covid.dao;

import java.util.List;

import com.cmr.covid.model.PatientPojo;

public interface PatientDao {

	List<PatientPojo> getPatientsList();

	int getActivePatientsCount();

	int getInActivePatientsCount();
	
	int getTotalPatientsCount();

	String insertPatientPojo(PatientPojo patientPojo);

	List<PatientPojo> getActivePatientsByDoctorList(String doctor);

	PatientPojo getPatientDetailsbyEmail(String pemail);
	
	int updatePatientDetails(PatientPojo patientPojo);

	int updatePatientProfileDetails(PatientPojo patientPojo);

	List<PatientPojo> getActivePatientsActiveByDoctorList(String doctor);
}
