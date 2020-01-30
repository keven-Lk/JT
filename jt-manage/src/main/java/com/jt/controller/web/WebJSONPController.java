package com.jt.controller.web;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.ItemDesc;

@RestController
public class WebJSONPController {
//	跨域数据封装格式::callback(json)
	//获取数据之后转化为json串 json
	@RequestMapping("/web/testJSONP")
	public JSONPObject  testJSONPObject(String callback) {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(10010L)
		        .setItemDesc("JSONP的跨域访问!!!!")
		        .setCreated(new Date())
		        .setUpdated(new Date());
		return new JSONPObject(callback, itemDesc);

	}
}
