package com.reece.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class PersonalLoginUrlAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	PersonalLoginUrlAuthenticationEntryPoint(String loginFormUrl){
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		PersonalLoginUrlAuthenticationEntryPoint point = 
				new PersonalLoginUrlAuthenticationEntryPoint("/login.html");
		//创建登录页面
		String redirectUrl = point.buildRedirectUrlToLoginPage(request, response, authException);
		//实现转发
		point.redirectStrategy.sendRedirect(request, response, redirectUrl);
	}

}
