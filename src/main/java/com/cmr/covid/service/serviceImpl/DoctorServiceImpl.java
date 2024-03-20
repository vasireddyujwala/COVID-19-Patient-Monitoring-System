package com.cmr.covid.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmr.covid.dao.DoctorDao;
import com.cmr.covid.dao.PatientDao;
import com.cmr.covid.model.DoctorPojo;
import com.cmr.covid.model.PatientPojo;
import com.cmr.covid.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDao doctorDao;

	@Autowired
	private PatientDao patientDao;

	@Override
	public List<PatientPojo> getPatientsList(String padoctor) {
		// TODO Auto-generated method stub
		return patientDao.getActivePatientsByDoctorList(padoctor);
	}

	@Override
	public DoctorPojo getDoctorProfile(String demail) {
		// TODO Auto-generated method stub
		return doctorDao.getDoctorDetailbyEmail(demail);
	}

	@Override
	public int UpdateDoctorDetails(DoctorPojo doctorPojo) {
		// TODO Auto-generated method stub
		return doctorDao.updateDoctorDetails(doctorPojo);
	}

	@Override
	public List<DoctorPojo> getDoctorList() {
		// TODO Auto-generated method stub
		return doctorDao.getDoctorsList();
	}

	@Override
	public List<PatientPojo> getPatientsActiveList(String padoctor) {
		// TODO Auto-generated method stub
		return patientDao.getActivePatientsActiveByDoctorList(padoctor);
	}

}
