package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.config.WxPayConfig;
import com.dao.WxPayConfigDao;

@Repository
public class WxPayConfigDaoImpl implements WxPayConfigDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public WxPayConfig getWxPayConfig() {
		List<WxPayConfig> WxPayConfigs = jdbcTemplate.query("SELECT APP_ID,MCH_ID,"
				+ "WX_KEY,CERT_PATH,WX_DOMAIN,PRIMARY_DOMAIN,NOTIFY_URL,AUTO_REPORT,"
				+ "USE_SANDBOX,SANDBOX_SIGN_KEY  "
				+ " FROM WX_PAY_CONFIG",
				new RowMapper<WxPayConfig>(){

			@Override
			public WxPayConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
				WxPayConfig WxPayConfig = new WxPayConfig();
				WxPayConfig.setAppId(rs.getString("APP_ID"));
				WxPayConfig.setAutoReport(rs.getString("AUTO_REPORT"));
				WxPayConfig.setCertPath(rs.getString("CERT_PATH"));
				WxPayConfig.setKey(rs.getString("WX_KEY"));
				WxPayConfig.setMchId(rs.getString("MCH_ID"));
				WxPayConfig.setNotifyUrl(rs.getString("NOTIFY_URL"));
				WxPayConfig.setPrimaryDomain(rs.getString("PRIMARY_DOMAIN"));
				WxPayConfig.setUseSandbox(rs.getString("USE_SANDBOX"));
				WxPayConfig.setWxDomain(rs.getString("WX_DOMAIN"));
				WxPayConfig.setSandboxSignKey(rs.getString("SANDBOX_SIGN_KEY"));
				return WxPayConfig;
			}
			
		});
		if(WxPayConfigs != null && WxPayConfigs.size() > 0){
			return WxPayConfigs.get(0);
		}
		return null;
	}

	@Override
	public int update(WxPayConfig wxPayConfig) {
		return jdbcTemplate.update("UPDATE WX_PAY_CONFIG SET SANDBOX_SIGN_KEY = ? ", 
				wxPayConfig.getSandboxSignKey());

	}

}
