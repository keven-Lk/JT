package com.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.jt.pojo.User;

public interface UserMapper {
	/**
	 * Mybatis提供简单的注解形式,如果是单表查询
	 * 可以通过注解实现.
	 * @return
	@Select("select * from user")
	@Insert("sql")
	@Update("sql")
	@Delete("sql")
	 */
	@Select("select * from user")
	List<User> findAll();
	
	//与映射文件中的sql语句相同
	@Insert("insert into user(id,name,age,sex) values(null,#{name},#{age},#{sex})")
	void addUser(User user);
}
