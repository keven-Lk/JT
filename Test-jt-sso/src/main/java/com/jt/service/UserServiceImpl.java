package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * param:用户输入的内容
	 * type:1.username 2.phone 3.email
	 * sql:select count(*) from tb_user where username="张三";
	 * 经过分析:首先应该将1 2 3转化为具体的数据库字段
	 */
	@Override
	public Boolean findcheckUser(String param, Integer type) {
		String colunm = null;
		switch (type) {
		case 1:
			colunm="username";
			break;
		case 2:
			colunm="phone";
			break;
		case 3:
			colunm="email";
			break;
		}
		//根据数据库结果分析如果总数为0返回false 否则返回true
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(colunm, param);
		int count = userMapper.selectCount(queryWrapper);

		return count==0?false:true;
	}
}
