package com.reece.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.reece.spring.security.tool.AntUrlPathMatcher;
import com.reece.spring.security.tool.UrlMatcher;

/**
 * 加载所有URL所需的权限信息
 * @author Rihui Zhang
 *
 */
public class MyInvocationSecurityMetaDatasource implements
		FilterInvocationSecurityMetadataSource {
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	// tomcat启动时实例化一次
	public MyInvocationSecurityMetaDatasource() {
		loadResourceDefine();
	}

	// tomcat开启时加载一次，加载所有url和权限（或角色）的对应关系
	private void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		ConfigAttribute ca = new SecurityConfig("ROLE_USER");
		atts.add(ca);
		resourceMap.put("/index.jsp", atts);
		resourceMap.put("/toindex.do", atts);
	}

	// 参数是要访问的url，返回这个url对应的所有权限（或角色）
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		System.out.println("进入MyInvocationSecurityMetaDatasource的getAttributes方法...");
		// 将参数转为url
		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
}
