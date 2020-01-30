package com.jt.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemCat;

public class TestObjectMapper {
	//测试1:将java对象转化为Json串
	@Test
	public void testToJSON() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ItemCat itemCat = new ItemCat();
		itemCat.setId(1000L)
				.setName("测试")
				.setParentId(2000L);
		//将对象转化为json串 必须调用对象的get/set方法
		String json = mapper.writeValueAsString(itemCat);
		System.out.println(json);
		//将json串转化为对象
		ItemCat jsonItemCat = mapper.readValue(json, ItemCat.class);
		System.out.println(jsonItemCat);
	}
	
	//转化list集合数据
	@Test
	public void testList() throws Exception {
		List<ItemCat> catList = new ArrayList<ItemCat>();
		for(int i=0;i<5;i++) {
			ItemCat itemCat = new ItemCat();
			itemCat.setId(1000L+i)
					.setName("测试"+i)
					.setParentId(2000L+i);
			catList.add(itemCat);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(catList);
		System.out.println(json);
		
		//将json转化为list
		List<ItemCat> jsonCatList = 
				objectMapper.readValue(json, catList.getClass());
		System.out.println(jsonCatList);
		
	}
	
}
