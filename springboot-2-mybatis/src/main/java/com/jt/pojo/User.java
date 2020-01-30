package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName
//实现对象与表映射关系
//规则:如果对象的名称与表名称一致则可以省略不写
public class User {
	@TableId(type=IdType.AUTO)	//定义主键
	private Integer id;
	private String name;
	private Integer age;
	private String sex;
}
