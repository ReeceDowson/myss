package com.reece.spring.security;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.reece.spring.security.service.AuthorityService;
import com.reece.spring.security.service.MyUserDetailService;
import com.reece.spring.security.service.PasswordCheckService;
@Service("myAuthenticationProvider")
public class MyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	@Resource(name="userDetalsService")
	private MyUserDetailService userDetailsService;
	
	@Autowired
	private PasswordCheckService passwordCheckService;

	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		System.out.println("进入MyAuthenticationProvider，对用户名和密码进行验证处理...");
		
		String username = (String)auth.getPrincipal();
		String password = (String)auth.getCredentials();
		System.out.println(password);
		
		//增加密码校验
		boolean isPasswordRight = passwordCheckService.isPasswordRight(username, password);
		if(!isPasswordRight){
			throw new AuthenticationCredentialsNotFoundException("密码错误！");
		}
		
		UserDetails user = userDetailsService.loadUserByUsername(username);
		
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,password,authorities);
		
		//讲验证通过后的信息重新封装成一个验证成功的对象返回
		return createSuccessAuthentication(username, authentication, user);
	}

	public MyUserDetailService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(MyUserDetailService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
