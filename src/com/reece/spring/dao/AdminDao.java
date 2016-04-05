package com.reece.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.reece.spring.annotations.MyBatisRepository;
import com.reece.spring.entity.Admin;

@Repository("adminDao")
@MyBatisRepository
public interface AdminDao {
	
	List<Admin> findAll();
	
	Admin findByAdminCode(String adminCode);
}
