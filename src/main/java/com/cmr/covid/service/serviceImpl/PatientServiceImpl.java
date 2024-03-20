package com.cmr.covid.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmr.covid.dao.DoctorDao;
import com.cmr.covid.dao.PatientDao;
import com.cmr.covid.model.PatientPojo;
import com.cmr.covid.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientDao patientDao;

	@Override
	public PatientPojo getPatientProfile(String pemail) {
		// TODO Auto-generated method stub
		return patientDao.getPatientDetailsbyEmail(pemail);
	}

	@Override
	public int updatePatientDetails(PatientPojo patientPojo) {
		// TODO Auto-generated method stub
		return patientDao.updatePatientDetails(patientPojo);
	}

	@Override
	public int updatePatientProfileDetails(PatientPojo patientPojo) {
		// TODO Auto-generated method stub
		return patientDao.updatePatientProfileDetails(patientPojo);
	}

}
