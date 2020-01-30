package com.jt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.pojo.User;
import com.jt.util.HttpClientService;

@Service
public class HCUserServiceImpl implements HCUserService{
	@Autowired
	private HttpClientService httpClient;
	
	@Override
	public void saveUser(User user) {
		String url = "http://sso.jt.com/1812/save";
		Map<String,String> params = new HashMap<>();
		String username = user.getUsername();
		String pass = user.getPassword();
		String phone = user.getPhone();
		String created = new Date().toString();
		String updated = created;
		params.put("username", username);
		params.put("phone", phone);
		params.put("created", created);
		params.put("updated", updated);
		params.put("pass", pass);
		String userJson = httpClient.doPost(url, params);
	}
	
}
