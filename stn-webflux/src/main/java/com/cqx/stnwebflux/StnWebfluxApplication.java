package com.cqx.stnwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class StnWebfluxApplication {

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(StnWebfluxApplication.class, args);
    }

}
