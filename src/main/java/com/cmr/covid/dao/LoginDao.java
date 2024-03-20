package com.cmr.covid.dao;

import com.cmr.covid.model.VerifyOtp;

public interface LoginDao {

	int verifyOtp(VerifyOtp verifyOtp);

	int forgotPwd(VerifyOtp verifyOtp);
	
	int setUserActive(String verifyOtp);

}
