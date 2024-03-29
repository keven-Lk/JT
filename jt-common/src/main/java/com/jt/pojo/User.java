package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@TableName("tb_user")
@Data
@Accessors(chain = true)
public class User extends BasePojo{
	@TableId(type=IdType.AUTO)	//主键自增
	private Long id;	//用户id
	private String username;	//用户名
	private String password; //加密保存 md5
	private String phone;	//电话号码
	private String email;	//电子邮箱
}
