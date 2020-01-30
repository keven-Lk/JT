package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

@Controller	//对象需要交给spring容器管理 	id userController
public class UserController {
	@Autowired	//在实例化对象前必须先注入userService
	private UserService userService;
	
	//一般将服务器数据都会封装到域对象中保存
	//JSP:page域 request域 Session域 application域(tomcat容器域)
	@RequestMapping("/findAll")
	public String findAll(Model model) {
		List<User> userList = userService.findAll();
		//利用request对象将数据封装到域中
		model.addAttribute("userList",userList);
		//利用response对象将对象转发展现
		return "user_list";
	}
	
	/**
	 * SpringMVC可以接受用户提交数据,之后调用setXXX()
	 * 为属性赋值.
	 * @param user
	 * @return
	 */
	//实现用户新增操作
	@RequestMapping("/addUser")
	@ResponseBody	//回传json数据
	public SysResult addUser(User user) {
		try {
			userService.addUser(user);
			return new SysResult(200,"用户入库成功!!!",null);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(201, "用户入库失败!!!", null);
		}
	}
	
	//实现get乱码处理 get乱码处理方式1.取巧做法:修改tomcat中字符集编码
		//手动编解码 先将数据采用iso-8859-1的格式转换为字节数组 之后再用utf-8转化为字符串
		//工作用法,一般会自定义getFilter解决全站乱码问题
	@RequestMapping("/getAddUser")
	@ResponseBody
	public SysResult getAddUser(User user) {
		try {
			byte[] nameBys = user.getName().getBytes("ISO-8859-1");
			String name = new String(nameBys,"UTF-8");
			user.setName(name);
			System.out.println(user);
			
			
			userService.addUser(user);
			return new SysResult(200, "用户入库成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new SysResult(201, "用户入库失败!!!", null);
		}
	}
	
}
