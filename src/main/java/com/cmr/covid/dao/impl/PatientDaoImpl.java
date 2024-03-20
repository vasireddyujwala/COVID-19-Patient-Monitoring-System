package com.cmr.covid.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.cmr.covid.dao.PatientDao;
import com.cmr.covid.model.PatientPojo;

@Repository
public class PatientDaoImpl implements PatientDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<PatientPojo> getPatientsList() {
		String sql = "SELECT * FROM patientinfo";

		List<PatientPojo> patientPojo = jdbcTemplate.query(sql, new BeanPropertyRowMapper(PatientPojo.class));

		return patientPojo;
	}

	@Override
	public int getActivePatientsCount() {
		String sql = "SELECT count(*) FROM patientinfo where pstatus = '1'";

		int count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}

	@Override
	public int getInActivePatientsCount() {
		String sql = "SELECT count(*) FROM patientinfo where pstatus = '0'";

		int count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}

	@Override
	public int getTotalPatientsCount() {
		String sql = "SELECT count(*) FROM patientinfo";

		int count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}

	@Override
	public String insertPatientPojo(PatientPojo patientPojo) {

		String s = "";
		try {

			jdbcTemplate.update("call insertpatientinfo(?, ?, ?, ?, ?, ?)", patientPojo.getpName(),
					patientPojo.getpEmail(), patientPojo.getPphno(), patientPojo.getpAddress(),
					patientPojo.getPadoctor(), patientPojo.getpPwd());

		} catch (Exception e) {

			e.printStackTrace();
			s = e.getMessage();

		}

		return s;
	}

	@Override
	public List<PatientPojo> getActivePatientsByDoctorList(String doctor) {
		String sql = "SELECT * FROM patientinfo where padoctor =" + "'" + doctor + "'";

		List<PatientPojo> patientPojo = jdbcTemplate.query(sql, new BeanPropertyRowMapper(PatientPojo.class));

		return patientPojo;
	}

	@Override
	public List<PatientPojo> getActivePatientsActiveByDoctorList(String doctor) {
		String sql = "SELECT * FROM patientinfo where pstatus ='1' AND padoctor =" + "'" + doctor + "'";

		List<PatientPojo> patientPojo = jdbcTemplate.query(sql, new BeanPropertyRowMapper(PatientPojo.class));

		return patientPojo;
	}

	@Override
	public PatientPojo getPatientDetailsbyEmail(String pemail) {
		// TODO Auto-generated method stub

		String sql = "select * from patientinfo where pemail =" + "'" + pemail + "'";

		return jdbcTemplate.query(sql, new ResultSetExtractor<PatientPojo>() {

			@Override
			public PatientPojo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					PatientPojo patientPojo = new PatientPojo();
					patientPojo.setPid(rs.getString("pid"));
					patientPojo.setpName(rs.getString("pname"));
					patientPojo.setPphno(rs.getString("pphno"));
					patientPojo.setpEmail(rs.getString("pemail"));
					patientPojo.setpAddress(rs.getString("paddress"));
					patientPojo.setPadoctor(rs.getString("padoctor"));
					patientPojo.setpBp(rs.getString("pbp"));
					patientPojo.setpPr(rs.getString("ppr"));
					patientPojo.setpTemp(rs.getString("ptemp"));
					patientPojo.setpStatus(rs.getString("pstatus"));
					return patientPojo;
				}

				return null;
			}

		});
	}

	@Override
	public int updatePatientDetails(PatientPojo patientPojo) {
		try {

			String updateQuery = "update patientinfo set pbp = ? , ptemp = ? , ppr = ? , pstatus = ?  where pemail = ?";

			return jdbcTemplate.update(updateQuery, patientPojo.getpBp(), patientPojo.getpTemp(), patientPojo.getpPr(),
					patientPojo.getpStatus(), patientPojo.getpEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updatePatientProfileDetails(PatientPojo patientPojo) {
		try {

			String updateQuery = "update patientinfo set pname = ? , pphno = ? , paddress = ?  where pemail = ?";

			return jdbcTemplate.update(updateQuery, patientPojo.getpName(), patientPojo.getPphno(),
					patientPojo.getpAddress(), patientPojo.getpEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
