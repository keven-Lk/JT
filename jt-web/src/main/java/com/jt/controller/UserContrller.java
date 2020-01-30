package com.jt.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/user")
public class UserContrller {
	//消费者负责调用接口
	@Reference(timeout=3000,check=false)
	private DubboUserService userService;
	@Autowired
	private JedisCluster jedisCluster;
	
	//实现用户新增
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUser(User user) {
		try {
			userService.saveUser(user);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail();
		}
	}
	
	/**
	 * 实行用户单点登录
	 * 
	 * @param user
	 * @return
	 * 返回值的要求
	 * 	如果用户名或密码错误,则token为null
	 * 	如果用户名返回正确,token中有数据
	 * 业务流程:
	 * 1.判断数据是否有效
	 * 2.如果有效则保存到cookie中
	 * 
	 * 关于Cookie补充:
	 * 	1.setPath("/")
	 * 		只要是jt项目中的页面都可以访问这个cookie
	 * 		.setPath("/sso/")
	 * 		只有位于sso/下面的页面才可以访问这个cookie
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult login(User user,HttpServletResponse response) {
		try {
			String token = userService.findUserByUP(user);
			//下行代码表示用户名和密码正确
			if(!StringUtils.isEmpty(token)) {
				Cookie cookie = new Cookie("JT_TICKET",token);
				cookie.setMaxAge(7*24*3600);
				cookie.setPath("/");	//cookie的权限 一般默认'/'
				response.addCookie(cookie);
				return SysResult.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.fail();
	}
	
	//实现用户登出操作 删除redis/cookie
	/**
	 * 1.先获取用户浏览器端的cookie数据	JT_TICKET
	 * 2.根据token数据删除redis
	 * 3.删除cookie
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		String token = null;
		//1.获取cookie数据
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("JT_TICKET".equals(cookie.getName())) {
				token = cookie.getValue();
				break;	//结束循环
			}
		}
		//2.删除redis数据
		jedisCluster.del(token);
		//3.删除cookie
		Cookie cookie = new Cookie("JT_TICKET", "");
		//0:立即删除 -1:关闭会话时删除 
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/index.html"; 
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
//	@RequestMapping("/doRegister")
//	public xxxx saveUser(User user) {
//		//让user 入库
//	}
}
