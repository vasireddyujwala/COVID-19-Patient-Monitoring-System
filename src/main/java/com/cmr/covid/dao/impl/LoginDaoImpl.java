package com.cmr.covid.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cmr.covid.dao.LoginDao;
import com.cmr.covid.model.VerifyOtp;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int verifyOtp(VerifyOtp verifyOtp) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO verfyotp(vemail, otpcode) VALUES(?,?)";

		return jdbcTemplate.update(sql, verifyOtp.getEmail(), verifyOtp.getPassword());
	}

	@Override
	public int forgotPwd(VerifyOtp verifyOtp) {
		try {

			String updateQuery = "update logins set password = ? , enabled = 1 where username = ?";

			return jdbcTemplate.update(updateQuery, verifyOtp.getPassword(), verifyOtp.getEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int setUserActive(String verifyOtp) {
		try {

			String updateQuery = "update logins set enabled = 1 where username = ?";

			return jdbcTemplate.update(updateQuery, verifyOtp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
