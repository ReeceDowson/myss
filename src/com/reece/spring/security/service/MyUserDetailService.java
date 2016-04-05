package com.reece.spring.security.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reece.spring.dao.AdminDao;
import com.reece.spring.entity.Admin;

@Service("userDetalsService")
public class MyUserDetailService implements UserDetailsService {
	
	private static AdminDao adminDao;
	
	@Resource(name="authorityService")
	private AuthorityService authorityService;
	
	static{
		ApplicationContext txt = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		adminDao = txt.getBean("adminDao",AdminDao.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("调用了UserDetailService的方法......");
		Admin admin = adminDao.findByAdminCode(username);
		System.out.println("admin:" + admin);
		if(admin == null){
			throw new UsernameNotFoundException("无此用户");
		}
		Collection<GrantedAuthority> authorities = authorityService.getAuthoritiesByUsername(username);
		
		User user = new User(username, admin.getPassword(), true, true, true, true, authorities);
		System.out.println("授予的权限有：" + user.getAuthorities());
		System.out.println("验证通过......");
		return user;
	}

}
