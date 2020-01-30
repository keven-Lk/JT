package com.jt.dubbo.service;
//这是第三方中立的接口定义,提供者需要实现接口,消费者需要调用接口

import java.util.List;

import com.jt.dubbo.pojo.User;

public interface UserService {
	//获取后台userList数据
	List<User> findAll();
	//获取后台返回值信息
	String getMsg();
}
