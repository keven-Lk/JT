package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIList;

public interface ItemService {
	
	EasyUIList findItemByPage(Integer page, Integer rows);

	void saveItem(Item item,ItemDesc itemDesc);

	void updataStatus(Long[] ids, int status);

	void updataItem(Item item, ItemDesc itemDesc);

	void deleteItem(Long[] ids);

	ItemDesc findItemDescByid(Long itemId);

	//根据id查询商品信息
	Item findItemByid(Long itemId);
	
}
