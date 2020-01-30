package com.jt.vo;
//与页面交互的vo对象
public class SysResult {
	private Integer statusInteger; //200表示数据执行成功 201表示执行失败
	private String msg;		//给用户提示信息
	private Object dbDate;	//表示服务器的数据返回
	public Integer getStatusInteger() {
		return statusInteger;
	}
	public void setStatusInteger(Integer statusInteger) {
		this.statusInteger = statusInteger;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getDbDate() {
		return dbDate;
	}
	public void setDbDate(Object dbDate) {
		this.dbDate = dbDate;
	}
	
	
	
	public SysResult(Integer statusInteger, String msg, Object dbDate) {
		super();
		this.statusInteger = statusInteger;
		this.msg = msg;
		this.dbDate = dbDate;
	}
	@Override
	public String toString() {
		return "SysResult [statusInteger=" + statusInteger + ", msg=" + msg + ", dbDate=" + dbDate + "]";
	}
	
	
	
}

