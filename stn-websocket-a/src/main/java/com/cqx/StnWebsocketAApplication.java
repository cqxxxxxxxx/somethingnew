package com.cqx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class StnWebsocketAApplication {

	public static void main(String[] args) {
		SpringApplication.run(StnWebsocketAApplication.class, args);
	}
}
