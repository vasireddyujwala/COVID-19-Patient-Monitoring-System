package com.cmr.covid.service;

import com.cmr.covid.model.PatientPojo;
import com.cmr.covid.model.VerifyOtp;

public interface LoginService {

	String addNewPatient(PatientPojo patientPojo);

	int addVerifyOtp(VerifyOtp verifyOtp);
	
	int forgotPwd(VerifyOtp verifyOtp);
	
	int setUserActive(String verifyOtp);

}
