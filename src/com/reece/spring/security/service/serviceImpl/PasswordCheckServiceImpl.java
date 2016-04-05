package com.reece.spring.security.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reece.spring.dao.AdminDao;
import com.reece.spring.security.service.PasswordCheckService;
@Service("passwordCheckService")
public class PasswordCheckServiceImpl implements PasswordCheckService{

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public boolean isPasswordRight(String username,String password) {
		String passwordRight = adminDao.findByAdminCode(username).getPassword();
		if(passwordRight.equals(password)){
			return true;
		}
		return false;
	}
	
}
