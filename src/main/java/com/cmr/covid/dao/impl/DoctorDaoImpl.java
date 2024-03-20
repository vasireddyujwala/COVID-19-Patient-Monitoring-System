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

import com.cmr.covid.dao.DoctorDao;
import com.cmr.covid.model.DoctorPojo;
import com.cmr.covid.model.PatientPojo;

@Repository
public class DoctorDaoImpl implements DoctorDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<DoctorPojo> getDoctorsList() {
		String sql = "SELECT * FROM doctorinfo";

		List<DoctorPojo> doctorPojo = jdbcTemplate.query(sql, new BeanPropertyRowMapper(DoctorPojo.class));

		return doctorPojo;
	}

	@Override
	public int getActiveDoctorsCount() {
		String sql = "SELECT count(*) FROM doctorinfo where dstatus = '1'";

		int count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}

	@Override
	public int getInActiveDoctorsCount() {
		String sql = "SELECT count(*) FROM doctorinfo where dstatus = '0'";

		int count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}

	@Override
	public int getTotalDoctorsCount() {
		String sql = "SELECT count(*) FROM doctorinfo";

		int count = jdbcTemplate.queryForObject(sql, Integer.class);

		return count;
	}

	@Override
	public int insertDoctorPojo(DoctorPojo doctorPojo) {
		int value = jdbcTemplate.update("call insertdoctorinfo(?, ?, ?, ?, ?, ?)", doctorPojo.getdName(),
				doctorPojo.getdPh(), doctorPojo.getdEmail(), doctorPojo.getdDesc(), doctorPojo.getDaddress(),
				doctorPojo.getdPwd());
		return value;
	}

	@Override
	public DoctorPojo getDoctorDetailbyEmail(String demail) {
		// TODO Auto-generated method stub

		String sql = "select * from doctorinfo where demail =" + "'" + demail + "'";

		return jdbcTemplate.query(sql, new ResultSetExtractor<DoctorPojo>() {

			@Override
			public DoctorPojo extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					DoctorPojo doctorPojo = new DoctorPojo();
					doctorPojo.setDid(rs.getString("did"));
					doctorPojo.setdName(rs.getString("dname"));
					doctorPojo.setdPh(rs.getString("dph"));
					doctorPojo.setdEmail(rs.getString("demail"));
					doctorPojo.setdDesc(rs.getString("ddesc"));
					doctorPojo.setDaddress(rs.getString("daddress"));
					doctorPojo.setDstatus(rs.getString("dstatus"));
					return doctorPojo;
				}

				return null;
			}

		});
	}

	@Override
	public int updateDoctorDetails(DoctorPojo doctorPojo) {
		try {

			String updateQuery = "update doctorinfo set dname = ? , dph = ? , daddress = ? , ddesc = ? where demail = ?";

			return jdbcTemplate.update(updateQuery, doctorPojo.getdName(), doctorPojo.getdPh(),
					doctorPojo.getDaddress(), doctorPojo.getdDesc(), doctorPojo.getdEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
