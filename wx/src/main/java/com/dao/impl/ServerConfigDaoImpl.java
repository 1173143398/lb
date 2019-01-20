package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.config.ServerConfig;
import com.dao.ServerConfigDao;

@Repository
public class ServerConfigDaoImpl implements ServerConfigDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public ServerConfig getServerConfig(String msgType,String eventType) {
		return jdbcTemplate.queryForObject("SELECT MSG_TYPE,EVENT_TYPE,REQ_RESP_TYPE,CONFIG_ID FROM SERVER_CONFIG WHERE MSG_TYPE = ? AND EVENT_TYPE=?",
				new RowMapper<ServerConfig>(){

			@Override
			public ServerConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
				ServerConfig serverConfig = new ServerConfig();
				serverConfig.setMsgType(rs.getString("MSG_TYPE"));
				serverConfig.setEventType(rs.getString("EVENT_TYPE"));
				serverConfig.setConfigId(rs.getString("CONFIG_ID"));
				return serverConfig;
			}
			
		},msgType,eventType);
	}

}
