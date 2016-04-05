package com.reece.spring.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class PersonalUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private String username;
	private String password;

	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException{
		System.out
		.println("进入PersonalUsernamePasswordFilter，对用户名和密码进行存储......");
		
		username = req.getParameter("adminCode");
		password = req.getParameter("password");
				
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		
		Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);
		
		return authentication;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
