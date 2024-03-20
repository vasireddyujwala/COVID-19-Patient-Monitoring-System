package com.cmr.covid.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmr.covid.dao.DoctorDao;
import com.cmr.covid.dao.PatientDao;
import com.cmr.covid.model.DoctorPojo;
import com.cmr.covid.model.PatientPojo;
import com.cmr.covid.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private PatientDao patientDao;

	@Override
	public Map<String, Integer> getAdminDashBoardDetails() {
		// TODO Auto-generated method stub
		Map<String, Integer> adminMap = new HashMap<String, Integer>();
		adminMap.put("ptcount", patientDao.getTotalPatientsCount());
		adminMap.put("dtcount", doctorDao.getTotalDoctorsCount());
		adminMap.put("ipcount", patientDao.getInActivePatientsCount());
		adminMap.put("apcount", patientDao.getActivePatientsCount());
		return adminMap;
	}

	@Override
	public List<PatientPojo> getPatientsList() {
		// TODO Auto-generated method stub
		return patientDao.getPatientsList();
	}

	@Override
	public List<DoctorPojo> getDoctorsList() {
		// TODO Auto-generated method stub
		return doctorDao.getDoctorsList();
	}

	@Override
	public int addDoctorInfo(DoctorPojo doctorpojo) {
		
		return doctorDao.insertDoctorPojo(doctorpojo);
	}


}
