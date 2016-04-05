package com.reece.spring.security.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public interface AuthorityService {
	
	Collection<GrantedAuthority> getAuthoritiesByUsername(String username);
}
