package com.jt.service;

import com.jt.pojo.User;

public interface DubboUserService {
	//在接口中添加方法,提供者实现方法
	void saveUser(User user);
	//dubbo接口定义实现用户单点登录
	String findUserByUP(User user);
	
}
