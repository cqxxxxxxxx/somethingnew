package com.cqx.springboot;

import com.cqx.springboot.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = UserMapper.class)
@SpringBootApplication
public class StnSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(StnSpringbootApplication.class, args);
    }

}
