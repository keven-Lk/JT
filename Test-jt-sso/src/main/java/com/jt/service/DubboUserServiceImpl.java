//package com.jt.service;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.DigestUtils;
//
//import com.alibaba.dubbo.config.annotation.Service;
//import com.jt.mapper.UserMapper;
//import com.jt.pojo.User; 
////jt-sso是服务的提供者,实现指定的接口
//@Service
//public class DubboUserServiceImpl implements DubboUserService{
//	@Autowired
//	private UserMapper userMapper;
//
//	@Override
//	@Transactional
//	public void saveUser(User user) {
////		String soltString = "cn.tedu.tarena";公司中加盐值
//		String md5Pass = 
//				DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
//		user.setPassword(md5Pass);
//		user.setEmail(user.getPhone());
//		user.setCreated(new Date());
//		user.setUpdated(user.getCreated());
//		userMapper.insert(user);
//	}
//}
