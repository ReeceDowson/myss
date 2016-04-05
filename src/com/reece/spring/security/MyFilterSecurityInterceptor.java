package com.reece.spring.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * URL访问拦截，从securityMetadataSource中获取每个URL所需的权限
 * 
 * @author Rihui Zhang
 * 
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor
		implements Filter {
	// 配置文件注入
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	// 登陆后，每次访问资源都通过这个拦截器拦截
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	public void invoke(FilterInvocation fi) throws IOException,
			ServletException {
		// fi里面有一个被拦截的url
		/*
		 * beforeInvocation这个方法，
		 * 它首先会调用MyInvocationSecurityMetadataSource类的getAttributes方法获取被拦截url所需的权限，
		 * 再调用MyAccessDecisionManager类decide方法判断用户是否够权限。
		 * */
		
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			// 执行下一个拦截器
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource newSource) {
		this.securityMetadataSource = newSource;
	}

	public void destroy() {

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
