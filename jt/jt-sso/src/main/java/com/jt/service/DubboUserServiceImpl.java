package com.jt.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;

import redis.clients.jedis.JedisCluster; 
//jt-sso是服务的提供者,实现指定的接口
@Service
public class DubboUserServiceImpl implements DubboUserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisCluster jedisCluster;
	@Override
	@Transactional
	public void saveUser(User user) {
//		String soltString = "cn.tedu.tarena";公司中加盐值
		String md5Pass = 
				DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		user.setEmail(user.getPhone());
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		userMapper.insert(user);
	}

	/**
	 * 步骤:
	 *  1.根据用户名和密码查询数据库
	 *  2.生成token串 MD5加密
	 *  3.把用户对象转化成json
	 */
	@Override
	public String findUserByUP(User user) {
		//1.将密码加密
		String md5Pass = 
				DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		//2.根据用户信息查询数据库
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.eq("username", user.getUsername())
					.eq("password", md5Pass);
		User userDB = userMapper.selectOne(queryWrapper);
		//3.判断用户是否正确
		if(userDB == null) {
			return null;	//回传null数据
		}
		//4.程序执行到这里 表示用户名和密码正确
		//md5("JT_TICKET"+System.currentTimeMillis()+user.getUsername());
		String token = "JT_TICKET"+System.currentTimeMillis()+user.getUsername();
		token = DigestUtils.md5DigestAsHex(token.getBytes());
		//必须进行脱敏处理
		userDB.setPassword("你猜猜!!!");
		String userJson = ObjectMapperUtil.toJSON(userDB);
		jedisCluster.setex(token, 7*24*3600, userJson);
		return token;
	}
}
