package com.reece.spring.security.service.serviceImpl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.reece.spring.dao.AdminDao;
import com.reece.spring.security.service.AuthorityService;
@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public Collection<GrantedAuthority> getAuthoritiesByUsername(String username) {
		
		String authoritiesString = adminDao.findByAdminCode(username).getAuthorities();
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesString);
	
		return grantedAuthorities;
	}

}
