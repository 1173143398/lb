package com.service.impl;

import org.springframework.stereotype.Service;

import com.service.OrderNoService;

@Service
public class OrderNoServiceImpl implements OrderNoService {

	public String getOrderNo(){
		return String.valueOf(System.currentTimeMillis());
	}
}
