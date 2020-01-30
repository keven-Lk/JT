package com.jt.config;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class WebServletConfig {
	/**
	 * 由伪静态实现servlet配置
	 * 添加2个请求拦截路径
	 * 	1.拦截后缀型请求 *.html
	 *  2.添加拦截前缀/service/*
	 *  以下配置相当于修改了web.xml中的servlet配置
	 *  
	 * @author Administrator
	 *springBoot中默认不开启后缀型路径 需要手动开启开关
	 */
//	@Bean
	public ServletRegistrationBean<Servlet> servletConfig(DispatcherServlet dispatcherServlet){
		ServletRegistrationBean<Servlet> servletBean =
				new ServletRegistrationBean<Servlet>(dispatcherServlet);	
		servletBean.getUrlMappings().clear();//清空之前的默认配置
		servletBean.addUrlMappings("/service/*","*.html");//添加两个拦截
		return servletBean;
	}
}
