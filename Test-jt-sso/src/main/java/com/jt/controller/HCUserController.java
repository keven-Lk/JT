package com.jt.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.pojo.User;
import com.jt.service.HCUserService;

@Controller
@RequestMapping("/1812")
public class HCUserController {
	@Autowired
	HCUserService hCUserService;
	
	@RequestMapping("/save")
	@ResponseBody
	public String HCUserSave(HashMap<String, String> parames) {
		hCUserService.HCUserSave(parames);
		return "123";
	}
}
