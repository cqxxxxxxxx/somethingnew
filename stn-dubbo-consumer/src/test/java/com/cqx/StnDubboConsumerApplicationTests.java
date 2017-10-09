package com.cqx;

import com.cqx.model.City;
import com.cqx.service.CityConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StnDubboConsumerApplicationTests {

	@Autowired
	CityConsumer cityConsumer;

	@Test
	public void contextLoads() {

		City city = cityConsumer.findCityByName("爱爱爱");
		System.out.println(city.getName());
	}

}
