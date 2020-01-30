package com.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;

//使用映射框架时,ById代表根据主键操作
public interface UserMapper extends BaseMapper<User>{
	//实现用户信息查询
	@Select("select * from user")
	List<User> findAll();
}