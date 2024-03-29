package com.jt.intercentor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.UserThreadLocal;

import redis.clients.jedis.JedisCluster;

/**
 * 核心:获取用户的访问数据/路径  resquest/response
 * @author Administrator
 *
 */
@Component
public class UserInterceptor implements HandlerInterceptor{
	//1.获取用户的cookie数据
	@Autowired
	private	JedisCluster JedisCluster;
	//完成用户校验,如果用户没有登录则跳转用户登录页面
	//如果用户已经完成登录则放行
	//boolean:true 放行   false:拦截+重定向
	
	/**
	 * 用户拦截器实现步骤
	 * 1.手下能获取用户的cookie数据
	 * 2.判断用户是否已经登录,如果用户没有登录则重定向到用户登录页面.
	 * 		如果用户已经登录,则判断redis中手否有数据.
	 * 			有:表示用户之前登录成功,予以放行
	 * 			无:表示用户登录失败,之后重定向到登录页面
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("JT_TICKET".equals(cookie.getName())) {
				token = cookie.getValue();
			}
		}
		if(!StringUtils.isEmpty(token)) {
			//判断redis中是否有数据
			String userJSON = JedisCluster.get(token);
			if(!StringUtils.isEmpty(userJSON)) {
				//程序执行到这里表示用户已经登录
				User user = ObjectMapperUtil.toObject(userJSON, User.class);
//				request.setAttribute("JT_USER", user);
				UserThreadLocal.set(user);
				System.out.println("拦截器启动,将数据传入Threadlocal");
				return true;
			}
		}
		//如果程序执行到这里,表示用户一定没有登录.
		response.sendRedirect("/user/login.html");
		return false;//表示拦截
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		UserThreadLocal.remove();	//删除线程数据
		System.out.println("用户本次调用完成,清空数据");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
