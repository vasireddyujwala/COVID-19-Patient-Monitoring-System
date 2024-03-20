package com.cmr.covid.dao;

import java.util.List;

import com.cmr.covid.model.DoctorPojo;

public interface DoctorDao {
	
	List<DoctorPojo> getDoctorsList();

	int getActiveDoctorsCount();

	int getInActiveDoctorsCount();
	
	int getTotalDoctorsCount();

	int insertDoctorPojo(DoctorPojo doctorPojo);

	DoctorPojo getDoctorDetailbyEmail(String demail);
	
	int updateDoctorDetails(DoctorPojo doctorPojo);

}
