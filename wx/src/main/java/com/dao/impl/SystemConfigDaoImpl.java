package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
		List<SystemConfig> systemConfigs = jdbcTemplate.query("SELECT SERVER_MSG_TYPE,APP_ID,APP_SECRET,ACCESS_TOKEN,TOKEN,TIMER_UPDATE_TOKEN_URL,"
				+ " TMS,EXPIRES_IN,JSAPI_TICKET,SECRITY_TYPE,ENCODING_AESKEY "
				+ " FROM SYSTEM_CONFIG",
				new RowMapper<SystemConfig>(){

			@Override
			public SystemConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
				SystemConfig systemConfig = new SystemConfig();
				systemConfig.setServerMsgType(rs.getString("SERVER_MSG_TYPE"));
				systemConfig.setAppId(rs.getString("APP_ID"));
				systemConfig.setAppSecret(rs.getString("APP_SECRET"));
				systemConfig.setAccessToken(rs.getString("ACCESS_TOKEN"));
				systemConfig.setTimerUpdateTokenUrl(rs.getString("TIMER_UPDATE_TOKEN_URL"));
				systemConfig.setTms(rs.getTimestamp("TMS"));
				systemConfig.setExpiresIn(rs.getInt("EXPIRES_IN"));
				systemConfig.setToken(rs.getString("TOKEN"));
				systemConfig.setJsapiTicket(rs.getString("JSAPI_TICKET"));
				systemConfig.setSecurityType(rs.getString("SECRITY_TYPE"));
				systemConfig.setEncodingAeskey(rs.getString("ENCODING_AESKEY"));
				return systemConfig;
			}
			
		});
		if(systemConfigs != null && systemConfigs.size() > 0){
			return systemConfigs.get(0);
		}
		return null;
	}

	@Override
	public int update(SystemConfig systemConfig) {
		return jdbcTemplate.update("UPDATE SYSTEM_CONFIG SET ACCESS_TOKEN = ? "
				+ ",EXPIRES_IN = ?,TMS = ?,JSAPI_TICKET = ? ", 
				systemConfig.getAccessToken(),systemConfig.getExpiresIn(),systemConfig.getTms(),systemConfig.getJsapiTicket());
	}

}
