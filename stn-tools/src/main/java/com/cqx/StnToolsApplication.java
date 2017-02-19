package com.cqx;

import com.cqx.hv.demo.Person;
import com.cqx.hv.demo.TestTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class StnToolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StnToolsApplication.class, args);
		Person person = new Person();
		person.setName("");
		person.setAge(3);
		person.setPhone("adsfdfasd");
		person.setEmail("saf@qq.com");

		TestTest testTest = new TestTest();
		testTest.aaa(person);
	}
}
