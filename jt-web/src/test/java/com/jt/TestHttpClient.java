package com.jt;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	/**
	 * 实行步骤
	 * 1.创建httpClient对象
	 * 2.指定url请求地址
	 * 3.指定请求的方式  get/post 
	 * 	规则:一般查询操作使用get请求.
	 * 		如果涉及数据入库/更新并且数据很大时采用post提交
	 * 		涉密操作采用post
	 * 4.发起请求response对象
	 * 5.判断请求是否正确 检查状态码200
	 * 6.从返回值对象中获取数据(json/html)之后进行转化
	 * @throws Exception 
	 * @throws  
	 * 
	 */
	//实现get请求
	@Test
	public void testGet() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url="https://www.baidu.com/s?ie=UTF-8&wd=%E6%82%A8%E7%9B%AE%E5%89%8D%E6%97%A0%E6%B3%95%E8%AE%BF%E9%97%AE%20www.jt.com%2C%E5%9B%A0%E4%B8%BA%E6%AD%A4%E7%BD%91%E7%AB%99%E4%BD%BF%E7%94%A8%E4%BA%86%20HSTS%E3%80%82%E7%BD%91%E7%BB%9C%E9%94%99%E8%AF%AF%E5%92%8C%E6%94%BB%E5%87%BB%E9%80%9A%E5%B8%B8%E6%98%AF%E6%9A%82%E6%97%B6%E7%9A%84%2C";
		HttpGet httpGet = new HttpGet(url);
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		if(httpResponse.getStatusLine().getStatusCode() == 200) {
			System.out.println("请求正确返回!!!!");
			String result = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(result);
		}
	}
}
