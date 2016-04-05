package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.reece.spring.dao.AdminDao;
import com.reece.spring.entity.Admin;

public class JunitTest {
	
	@Test
	public void test1(){
		ApplicationContext txt = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(txt.getBean("jdbc"));
	}
	
	@Test
	public void test2(){
		ApplicationContext txt = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminDao adminDao = txt.getBean("adminDao",AdminDao.class);
		System.out.println(adminDao);
	}
	
	@Test
	public void test3(){
		ApplicationContext txt = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminDao adminDao = txt.getBean("adminDao",AdminDao.class);
		Admin admin = adminDao.findByAdminCode("caocao");
		System.out.println(admin + "!");
	}
}
