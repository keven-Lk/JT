package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.javassist.runtime.Desc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIList;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUIList findItemByPage(Integer page, Integer rows) {
		//sql:select count(*) from tb)_item  
		int total = itemMapper.selectCount(null);
		/**
		 * 获取分页后的数据
		 * sql:
		 * 查询第一页	limit 起始位置,显示条数
		 * 	select * from tb_item limit 0,20
		 * 第二页:
		 * 	select * from tb_item limit 20,20
		 * 第三页:
		 * 	select * from tb_item limit 40,20
		 * 
		 * 第N页
		 *	select * from tb_item limit (n-1)*20,20 
		 * 
		 */
		int start = (page - 1) * rows;
		List<Item> itmList = itemMapper.findItemListByPage(start,rows); 
		
		return new EasyUIList(total, itmList);
	}

	/**
	 * 知识回顾:
	 * Propagation:事务传播属性
	 * 	1.REQUIRED:必须添加事务,默认值
	 * 	2.SUPPORTS:事务支持的
	 * 	例如:刚执行了save操作.之后执行了查询操作.会使用同一个事务.
	 * 	一般只有查询操作时才会添加SUPPORTS
	 * 	3.REQUIRES_NEW:会开启一个全新的事务.
	 * 	4.NEVER 从不添加事务.做爬虫操作时使用.
	 * 
	 * 异常处理机制:
	 * 	如果是运行时异常,则Spring负责事务回滚
	 * 	如果是编译异常(检查异常),spring不负责事务回滚.
	 * 	rollbackFor:异常的.class 指定异常类型回滚事务
	 *	noRollbackFor:异常的.class指定异常不会滚.
	 *	readOnly:当前操作数据库只读.
	 *
	 *实现商品信息同时入库
	 */
	@Override
	@Transactional()
	public void saveItem(Item item,ItemDesc itemDesc) {
		item.setStatus(1);//1.表示正常.2.下架
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		itemMapper.insert(item);
		
		itemDesc.setItemId(item.getId())
			.setCreated(item.getCreated())
			.setUpdated(item.getUpdated());
		itemDescMapper.insert(itemDesc);
	}

	/**
	 * 说明:该操作是一个批量更新操作
	 * sql:
	 * 		update tb_item_cat
	 * 		set status = #{},updated = #{updated}
	 * 		where id in (id1,id2,id3......)
	 * 实现A:面向对象的方式操作
	 * 实现B:自己写sql.当做作业
	 */
	@Override
	public void updataStatus(Long[] ids, int status) {
		Item item = new Item();
		item.setStatus(status).setUpdated(new Date());
		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<Item>();
		List<Long> idList = Arrays.asList(ids);
		updateWrapper.in("id", idList);
		itemMapper.update(item, updateWrapper);
	}

	@Override
	public void updataItem(Item item,ItemDesc itemDesc) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		
		itemDesc.setItemId(item.getId());
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);
	}
	
	@Override
	public void deleteItem(Long[] ids) {
		UpdateWrapper<Item> deleteWrapper = new UpdateWrapper<Item>();
		List<Long> idList = Arrays.asList(ids);
		itemMapper.deleteBatchIds(idList);
		itemDescMapper.deleteBatchIds(idList);
	}

	@Override
	public ItemDesc findItemDescByid(Long itemId) {
		return itemDescMapper.selectById(itemId);
	}

	@Override
	public Item findItemByid(Long itemId) {
		return itemMapper.selectById(itemId);
	}
	
	
	
	
	
	
}
