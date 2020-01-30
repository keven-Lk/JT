package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.service.HCUserService;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/user")
public class UserContrller {
	//消费者负责调用接口
	@Reference(timeout=3000,check=false)
	private DubboUserService userService;
	
	//实现用户新增
//	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUser(User user) {
		try {
			userService.saveUser(user);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail();
		}
	}
	
//	//实现页面通用跳转
//	@RequestMapping("/{moduleName}")
//	public String moduleName(@PathVariable String moduleName) {
//		return moduleName;
//	}
//	
	//作业:课上使用dubbo方式实现用户注册
	//自己联系使用httpClient技术实现注册
	//实现用户的注册
	@Autowired
	private HCUserService hcUserService;
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUserHC(User user) {
		//让user 入库
		hcUserService.saveUser(user);
		return SysResult.ok();
	}
}
