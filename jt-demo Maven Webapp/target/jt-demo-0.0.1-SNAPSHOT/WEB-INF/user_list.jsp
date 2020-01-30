<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>您好Springboot</title>
</head>
<body>
	<table border="1px" width="65%" align="center">
		<tr>
			<td colspan="6" align="center"><h3>学生信息</h3></td>
		</tr>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>性别</th>
			<th></th>
		</tr>
		<!-- 要求:从域对象中取值c标签 通过循环遍历获取数据信息之后打印 
			参数说明:
			1.items:表示从域对象中取值.jsp中的四大作用域
				${域中的key}获取List集合数据
			2.var:循环遍历时遍历的名称 user
			3.varStatus:记录当前循环的状态 获取遍历的次数
			4.${user.id}该方法调用的是对象中的getId方法获取
				所有pojo属性必须添加get/set方法
		-->
		<c:forEach items="${userList}" var="user">
			<tr align="center">
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.age}</td>
				<td>${user.sex}</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="http://www.baidu.com">百度页面</a>
	
</body>
</html>


