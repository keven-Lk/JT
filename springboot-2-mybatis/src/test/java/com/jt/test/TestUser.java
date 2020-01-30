package com.jt.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jt.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {
	
	@Autowired
	private UserService userService;
	//方法名称和类名严禁重名
	@Test
	public void testFindAll() {
		System.out.println(userService.findAll());
	}
	/**
	 * 1.从容器中获取对象
	 * 2.对象调用方法
	 */
//	@Test
//	public void testFindAll() {
//		ApplicationContext context = 
//				new ClassPathXmlApplicationContext("xml文件");
//		UserService userService = context.getBean(UserService.class);
//		userService.findAll();
//	}
}
