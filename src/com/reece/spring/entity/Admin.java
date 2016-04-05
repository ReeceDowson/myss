package com.reece.spring.entity;

import java.sql.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

@Repository
public class Admin {
	private int adminId;
	private String adminCode;
	private String password;
	private String name;
	private String telephone;
	private String email;
	private Date enrolldate;
	private String authorities;
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminCode() {
		return adminCode;
	}
	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getEnrolldate() {
		return enrolldate;
	}
	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}
	
	public String getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminCode=" + adminCode
				+ ", password=" + password + ", name=" + name + ", telephone="
				+ telephone + ", email=" + email + ", enrolldate=" + enrolldate
				+ ", authorities=" + authorities + "]";
	}
	
}
