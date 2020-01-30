package com.jt.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jt.pojo.User;
import com.jt.service.UserService;

public class TestSpring {
	//实例化Spring容器对象
	@Test
	public void test01() {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("/spring/application-1.xml");
		UserService userService = context.getBean(UserService.class);
		List<User> userList = userService.findAll();
		System.out.println(userList);
	}
}
