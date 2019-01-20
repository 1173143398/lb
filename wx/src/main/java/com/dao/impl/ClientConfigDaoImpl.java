package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.config.ClientConfig;
import com.dao.ClientConfigDao;

@Repository
public class ClientConfigDaoImpl implements ClientConfigDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public ClientConfig getClientConfig(String funcNo) {
		return jdbcTemplate.queryForObject("SELECT FUNC_NO,FUNC_DESC,REQ_RESP_TYPE,SHCEMA,URL,CONFIG_ID FROM CLILENT_CONFIG WHERE FUNC_NO = ?", new RowMapper<ClientConfig>() {

			@Override
			public ClientConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClientConfig clientConfig = new ClientConfig();
				clientConfig.setConfigId(rs.getString("CONFIG_ID"));
				clientConfig.setFuncDesc(rs.getString("FUNC_DESC"));
				clientConfig.setFuncNo(rs.getString("FUNC_NO"));
				clientConfig.setReqRespType(rs.getString("REQ_RESP_TYPE"));
				clientConfig.setShcema(rs.getString("SHCEMA"));
				clientConfig.setUrl(rs.getString("URL"));
				return clientConfig;
			}
			
		}, funcNo);
	}

}
