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
		return jdbcTemplate.queryForObject("SELECT FUNC_NO,FUNC_DESC,SHCEMA,URL,METHOD,REQ_CLASS,REQ_MSG_TYPE,"
				+ "RESP_CLASS,RESP_MSG_TYPE,SERVICE_BEAN "
				+ " FROM CLILENT_CONFIG WHERE FUNC_NO = ?", new RowMapper<ClientConfig>() {

			@Override
			public ClientConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
				ClientConfig clientConfig = new ClientConfig();
				clientConfig.setFuncDesc(rs.getString("FUNC_DESC"));
				clientConfig.setFuncNo(rs.getString("FUNC_NO"));
				clientConfig.setShcema(rs.getString("SHCEMA"));
				clientConfig.setMethod(rs.getString("METHOD"));
				clientConfig.setUrl(rs.getString("URL"));
				clientConfig.setReqClass(rs.getString("REQ_CLASS"));
				clientConfig.setReqMsgType(rs.getString("REQ_MSG_TYPE"));
				clientConfig.setRespClass(rs.getString("RESP_CLASS"));
				clientConfig.setRespMsgType(rs.getString("RESP_MSG_TYPE"));
				clientConfig.setServiceBean(rs.getString("SERVICE_BEAN"));
				return clientConfig;
			}
			
		}, funcNo);
	}

}
