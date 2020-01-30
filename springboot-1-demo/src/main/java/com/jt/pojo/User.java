package com.jt.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 要求通过yml文件实现对象的赋值操作
 * @author Administrator
 */

//@Controller		//标识controller
//@Service		//标识service层
//@Repository		//标识dao层
@Component		//管理对象 以上三种都是源于此注解
@ConfigurationProperties(prefix="user")
//在配置文件中查找user的前缀,之后如果属性值的名称与key的名称一致则利用对象的setXXX()进行属性赋值.必须添加set方法.
public class User {
	private Integer id;
	private String userName;
	private Integer age;
	private String sex;
	
//	@Value("${user.id}")//从spring中获取数据
//	private Integer id;
//	@Value("${user.userName}")	//无需添加set方法
//	private String userName;
//	@Value("${user.age}")
//	private Integer age;
//	@Value("${user.sex}")
//	private String sex;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + userName + ", age=" + age + ", sex=" + sex + "]";
	}
	
	
}
