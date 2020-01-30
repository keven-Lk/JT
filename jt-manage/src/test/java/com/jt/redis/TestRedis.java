package com.jt.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class TestRedis {
//如果需要从容器中获取容器时,才是用spring的测试方式.
	//1. 实现字符串的操作
	@Test
	public void testString() throws InterruptedException {
		Jedis jedis = new Jedis("192.168.96.132",6379);
		jedis.set("1812","tomcat猫");
		System.out.println(jedis.get("1812"));
		jedis.del("aaa"); //删除数据
//		jedis.keys("*");
//		设定超时时间
		jedis.expire("1812", 30);
		Thread.sleep(2000);
		System.out.println("key还能存活多久"+jedis.ttl("1812"));
	}
	
	@Test
	public void testHash() {
		//保存数据信息
		Jedis jedis = new Jedis("192.168.96.132",6379);
		jedis.hset("user","username","abc");
		boolean flag = jedis.hexists("user", "username");
		System.out.println(flag);
		Map<String, String> map = jedis.hgetAll("user");
		System.out.println(map);
		List<String> values = jedis.hvals("user");
		System.out.println(values);
		Set<String> set = jedis.hkeys("user");
		System.out.println(set);
	}
	
	//操作队列list集合
	@Test
	public void testList() {
		Jedis jedis = new Jedis("192.168.96.132",6379);
		//当做队列使用
		jedis.lpush("list","1","2","3","4","5");
		String result = jedis.rpop("list");
		System.out.println(result);
	}
		//操作事务
		@Test
		public void testTx() {
			Jedis jedis = new Jedis("192.168.96.132",6379);
			//开启事务
			Transaction transaction = jedis.multi();
			try {
				//set操作
				transaction.set("aaa","8999");
				transaction.set("ttt","19011");
				//事务提交
				int a = 1/0;
				transaction.exec();
			} catch (Exception e) {
				e.printStackTrace();
				transaction.discard();
			}
	}
	
}
