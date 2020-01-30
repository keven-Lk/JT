package com.jt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@Service	//实例化userServiceImpl对象
public class UserServiceImpl implements UserService{
	@Autowired	//实例化前 必须先注入userMapper对象
	private UserMapper userMapper;

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	//@Transactional//添加事务
	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
	}
}
