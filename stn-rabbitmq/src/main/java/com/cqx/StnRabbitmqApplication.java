package com.cqx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class StnRabbitmqApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StnRabbitmqApplication.class, args);
	}
}
