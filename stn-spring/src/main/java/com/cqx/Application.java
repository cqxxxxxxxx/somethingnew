package com.cqx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
//@EnableFeignClients(basePackages = {"com.cqx.feign"})
@SpringBootApplication
@EnableTransactionManagement
public class Application
{
    public static void main( String[] args )
    {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
//        UserDaoImpl indexController = ctx.getBean(UserDaoImpl.class);
//        indexController.auth("ss", "XX");
    }
}
