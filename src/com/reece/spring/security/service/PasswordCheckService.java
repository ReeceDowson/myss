package com.reece.spring.security.service;

public interface PasswordCheckService {
	boolean isPasswordRight(String username,String password);
}
