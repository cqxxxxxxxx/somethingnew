package com.cqx;

import org.ehcache.Cache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StnEhcacheApplicationTests {

	@Resource(name = "tokenCache1")	//按名注入
	Cache tokenCache1;
	@Resource(name = "tokenCache2")	//
	Cache tokenCache2;
	@Autowired
	Cache tokenCache3;

	@Test
	public void contextLoads() {

		System.out.println(tokenCache1.toString());
		tokenCache1.put("11", "11");
		System.out.println(tokenCache1.get("11"));

		System.out.println(tokenCache2.toString());
		tokenCache2.put("22", "22");
		System.out.println(tokenCache2.get("22"));
		tokenCache2.put("22", "2222");
		System.out.println(tokenCache2.get("22"));

		System.out.println(tokenCache3.toString());
		tokenCache3.put("33","33");
		System.out.println(tokenCache3.get("33"));
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(tokenCache3.get("33"));	//5秒后再输出 为null 因为cache设置为2秒后失效
	}

}
