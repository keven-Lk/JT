package com.jt.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

//测试redis分片技术
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestShardRedis {
	@Test
	public void test01() {
		//准备list集合封装多台redis
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("192.168.96.132",6379));
		shards.add(new JedisShardInfo("192.168.96.132",6380));
		shards.add(new JedisShardInfo("192.168.96.132",6381));
		ShardedJedis jedis = new ShardedJedis(shards);
		//当前操作的是redis的分片对象,操作3台redis
		jedis.set("hhhhhhhhh","分片机制成功");
		System.out.println(jedis);
	}
	
	//测试spring整合redis分片
	@Autowired
	private ShardedJedis jedis;
	@Test
	public void test02() {
		jedis.set("1812","spring整合成功");
		System.out.println(jedis.get("1812"));
	}
}
