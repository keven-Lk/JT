package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	/**
	 * 关于框架编码的说明:
	 * 1.使用旧的ssm框架3-4版本时,如果返回数据为String.则将数据通过@ResponseBody返回时,
	 * 	采用ISO-8859-1编码格式,所以返回数据必定乱码.
	 * 2.如果返回数据为对象时(pojo/vo),采用UTF-8格式会编码.
	 * 
	 * 当springBoot时,返回的数据都是UTF-8
	 * 	@RequestMapping(value="/queryItemName",
			produces="text/html;charset=utf-8")
		public String findItemCatNameById(Long itemCatId) {
			return itemCatService.findItemCatNameById(itemCatId);
	}
	 * 
	 * @param itemCatId
	 * @return
	 */
	//实现商品分类信息的查询
	@RequestMapping(value="/queryItemName",
			produces="text/html;charset=utf-8")
	public String findItemCatNameById(Long itemCatId) {
		//response.setContentType("text/html;charset=utf-8");//解决pose乱码信息
		return itemCatService.findItemCatNameById(itemCatId);
	}
	
	//实现商品分类信息树形结构展现
	/**
	 * defaultValue:如果没有传递参数,则设定默认值
	 * name:参数名称
	 * required:true/false 是否必须传递参数
	 * value:表示参数名称
	 * @param parentId
	 * @return
	 */
//	@RequestMapping("/list")
//	public List<EasyUITree> findItemCatList(
//			@RequestParam(value = "id",defaultValue="0") Long parentId){
//		//1.获取一级商品分类信息
//		return itemCatService.findItemCatList(parentId);
//	}
	@RequestMapping("/list")
	public List<EasyUITree> findItemCatList(
			@RequestParam(value = "id",defaultValue="0") Long parentId){
		//1.获取一级商品分类信息
		return itemCatService.findItemCatCacheList(parentId);
	}
}