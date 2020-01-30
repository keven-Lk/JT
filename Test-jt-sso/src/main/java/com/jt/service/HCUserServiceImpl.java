package com.jt.service;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@Service
public class HCUserServiceImpl implements HCUserService{
	@Autowired
	UserMapper userMapper;
	
	@Override
	public void HCUserSave(HashMap<String, String> parames) {
		String username;
		String pass;
		String phone;
		String created;
		String updated;
		
		username = parames.get("username");
		phone = parames.get("phone");
		created = parames.get("created");
		updated = parames.get("updated");
		pass = parames.get("pass");
		updated = parames.get("updated");
		
		User user = null;
		String md5Pass = 
				DigestUtils.md5DigestAsHex(pass.getBytes());
		user.setPassword(md5Pass);
		user.setEmail(user.getPhone());
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		userMapper.insert(user);
	}
}
