package com.cqx;

import com.cqx.config.MQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by BG307435 on 2017/9/13.
 */
@Component
public class Producer implements CommandLineRunner{

    private final RabbitTemplate rabbitTemplate;
    private final Consumer consumer;
    private final ConfigurableApplicationContext context;

    public Producer(Consumer consumer, RabbitTemplate rabbitTemplate,
                  ConfigurableApplicationContext context) {
        this.consumer = consumer;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(MQConfig.queueName, "Hello from RabbitMQ!");
        consumer.getLatch().await(10000, TimeUnit.SECONDS);
        context.close();
    }

}
