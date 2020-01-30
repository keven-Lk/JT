package com.jt.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * 该配置为配置类 代替之前配置文件 web.xml和spring配置文件
 * @author Administrator
 */
//@ImportResource("classpath:/spring/application-redis.xml")	//导入第三方配置文件 
@Configuration	//标识配置类 当springboot程序启动时,会加载配置类信息
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
//	
//	@Value("${redis.shards}")
//	private String redisShards;
//
//	
////	@Bean	//将方法的返回值的对象交给spring容器管理
////	public Jedis jedis() {
////		return new Jedis(host,port);
////	}
//	
//	
//	@Bean
//	public ShardedJedis shardJedis() {
//		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
//		String[] nodes = redisShards.split(",");
//		//node:IP端口
//		for(String node : nodes) {
//			String[] host_port = node.split(":");
//			JedisShardInfo info = 
//					new JedisShardInfo(host_port[0],host_port[1]);
//			shards.add(info);
//		}
//		//当前操作的是redis的分片对象,操作3台redis
//		return new ShardedJedis(shards);
//	}
//	@Value("${redis.nodes}")
//	private String nodes;
//	@Value("${redis.masterName}")
//	private String masterName;
//	
//	//获取哨兵的链接池
//	@Bean
//	public JedisSentinelPool jedisSentinelPool(){
//		Set<String> sentinels = new HashSet<String>();
//		sentinels.add(nodes);
//		return new JedisSentinelPool(masterName, sentinels);
//	}
	
	//实现redis集群引入
	@Value("${redis.nodes}")
	public String nodes;	//IP:port,Ip:prot,...
	
	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodesSet = new HashSet<HostAndPort>();
		String[] node = nodes.split(",");
		for (String h_pNode : node) {
			//ip:port
			String[] args = h_pNode.split(":");
			int port = Integer.parseInt(args[1]);
			HostAndPort hostAndPort = new HostAndPort(args[0],port);
			nodesSet.add(hostAndPort);
		}
		return new JedisCluster(nodesSet);
	}
	
}
