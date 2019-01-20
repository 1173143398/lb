package com.security;

public interface SecurityService {
 
	String encrypt(String message);
	
	String decrypt(String message);
}
