package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.config.SystemConfig;
import com.dao.SystemConfigDao;

@Repository
public class SystemConfigDaoImpl implements SystemConfigDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public SystemConfig getSystemConfig() {
		return jdbcTemplate.queryForObject("SELECT SERVER_MSG_TYPE,APP_ID,APP_SECRET,ACCESS_TOKEN,TIMER_UPDATE_TOKEN_URL "
				+ " FROM SYSTEMP_CONFIG",
				new RowMapper<SystemConfig>(){

			@Override
			public SystemConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
				SystemConfig systemConfig = new SystemConfig();
				systemConfig.setServerMsgType(rs.getString("SERVER_MSG_TYPE"));
				systemConfig.setAppId(rs.getString("APP_ID"));
				systemConfig.setAppSecret(rs.getString("APP_SECRET"));
				systemConfig.setAccessToken(rs.getString("ACCESS_TOKEN"));
				systemConfig.setTimerUpdateTokenUrl(rs.getString("TIMER_UPDATE_TOKEN_URL"));
				return systemConfig;
			}
			
		});
	}

	@Override
	public int update(SystemConfig systemConfig) {
		return jdbcTemplate.update("UPDATE SYSTEMP_CONFIG SET SERVER_MSG_TYPE = ?,APP_ID = ?,APP_SECRET = ?,ACCESS_TOKEN = ? "
				+ "TIMER_UPDATE_TOKEN_URL = ?,", systemConfig.getServerMsgType(),systemConfig.getAppId(),systemConfig.getAppSecret(),
				systemConfig.getAccessToken());
	}

}
