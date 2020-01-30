package com.jt.dubbo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.dubbo.mapper.UserMapper;
import com.jt.dubbo.pojo.User;
@Service(timeout=3000)	//3秒超时	必须使用dubbo的注解
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> findAll() {
		//表示没有where条件查询全部数据
		return userMapper.selectList(null);
	}

	@Override
	public String getMsg() {
		return "我是第二台9002提供者";
	}

}
