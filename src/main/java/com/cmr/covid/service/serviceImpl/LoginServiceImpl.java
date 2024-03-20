package com.cmr.covid.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmr.covid.dao.LoginDao;
import com.cmr.covid.dao.PatientDao;
import com.cmr.covid.model.PatientPojo;
import com.cmr.covid.model.VerifyOtp;
import com.cmr.covid.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	PatientDao patientDao;

	@Autowired
	LoginDao loginDao;

	@Override
	public String addNewPatient(PatientPojo patientPojo) {
		// TODO Auto-generated method stub
		return patientDao.insertPatientPojo(patientPojo);

	}

	@Override
	public int addVerifyOtp(VerifyOtp verifyOtp) {
		// TODO Auto-generated method stub
		return loginDao.verifyOtp(verifyOtp);
	}

	@Override
	public int forgotPwd(VerifyOtp verifyOtp) {
		// TODO Auto-generated method stub
		return loginDao.forgotPwd(verifyOtp);
	}

	@Override
	public int setUserActive(String verifyOtp) {
		// TODO Auto-generated method stub
		return loginDao.setUserActive(verifyOtp);
	}

}
