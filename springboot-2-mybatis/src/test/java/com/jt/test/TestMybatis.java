package com.jt.test;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatis {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void savaUser() {
		User user = new User();
		user.setName("测试mybatisplus")
			.setAge(19)
			.setSex("男");
		
		int rows = userMapper.insert(user);
		System.out.println("入库成功"+rows);
	}
	
	//数据库删除
	@Test
	public void delUser() {
//		userMapper.deleteById(55);
		
		//利用条件构造器删除 name = "促使mybatisplus"
//		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//		queryWrapper.eq("name","测试mybatisplus");
//		int rows = userMapper.delete(queryWrapper);
//		System.out.println("删除成功"+rows);
		
		//删除年龄小于5岁的 age < 5
		//= eq,  < lt ,  >gt
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.lt("age", 5);
		int rows = userMapper.delete(queryWrapper);
		System.out.println("删除成功"+rows);
	}
	
	//数据更新,sql语句是底层拼接而成的,拼接的原则是只拼接不为null的属性
	@Test
	public void updateUser() {
//		User user = new User();
//		user.setId(51);//where条件
//		user.setSex("女");//set 字段=值
//		user.setAge(25);//set
//		userMapper.updateById(user);
		
		/*
		 * 将name=小兰兰性别改为其他
		 * 参数说明:
		 * 		entity:需要修改的数据的值
		 * 		updateWrapper:条件构造器.拼接where条件的
		 */
		User user = new User();
		user.setSex("其他");
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
		updateWrapper.eq("name", "小兰兰");
		userMapper.update(user, updateWrapper);
	}
	
	@Test
	public void seleteUser() {
		User user = new User();
//		userMapper.selectList(queryWrapper);	//返回列表
//		userMapper.selectOne(queryWrapper)	//只查询一个
		User sb = userMapper.selectById(51);
		System.out.println(sb);
	}
}
