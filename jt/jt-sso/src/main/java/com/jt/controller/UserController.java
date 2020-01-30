package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

import ch.qos.logback.core.subst.Token;
import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private JedisCluster jedisCluster;
	
	/**
	 * 前台通过jsonp形式实现跨域请求,后台需要特殊个数
	 */
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject fidnCheckUser(
			@PathVariable String param,
			@PathVariable Integer type,
			String callback) {
		//返回true或者false true表示用户已经存在 false表示用户可以使用
		Boolean flag = userService.findcheckUser(param,type);
		return new JSONPObject(callback, SysResult.ok(flag));
	}
	
	//根据token数据获取用户信息
	@RequestMapping("/query/{token}")
	public JSONPObject findUserByTokey(@PathVariable String token,
			String callback) {
		String userJSON = jedisCluster.get(token);
		return new JSONPObject(callback, SysResult.ok(userJSON));
	}
	
}
