package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.config.ConfigDetail;
import com.dao.ConfigDetailDao;

@Repository
public class ConfigDetailDaoImpl implements ConfigDetailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public ConfigDetail getConfigDetail(String configId) {
		return jdbcTemplate.queryForObject("SELECT CONFIG_ID,REQ_CLASS,REQ_MSG_TYPE,REQ_MSG_PARSE_BEAN,REQ_MSG_VALID_BEAN,RESP_CLASS,"
				+ "RESP_MSG_TYPE,RESP_MSG_VALID_BEAN,RESP_MSG_PARSE_BEAN,SERVICE_BEAN FROM CONFIG_DETAIL WHERE CONFIG_ID = ?", 
				new RowMapper<ConfigDetail>() {

			@Override
			public ConfigDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				ConfigDetail configDetail = new ConfigDetail();
				configDetail.setConfigId(rs.getString("CONFIG_ID"));
				configDetail.setReqClass(rs.getString("REQ_CLASS"));
				configDetail.setReqMsgType(rs.getString("REQ_MSG_TYPE"));
				configDetail.setReqMsgParseBean(rs.getString("REQ_MSG_PARSE_BEAN"));
				configDetail.setReqMsgValidBean(rs.getString("REQ_MSG_VALID_BEAN"));
				configDetail.setRespClass(rs.getString("RESP_CLASS"));
				configDetail.setRespMsgType(rs.getString("RESP_MSG_TYPE"));
				configDetail.setRespMsgValidBean(rs.getString("RESP_MSG_VALID_BEAN"));
				configDetail.setRespMsgParseBean(rs.getString("RESP_MSG_PARSE_BEAN"));
				configDetail.setServiceBean(rs.getString("SERVICE_BEAN"));
				return configDetail;
			}},configId);
	}

}
