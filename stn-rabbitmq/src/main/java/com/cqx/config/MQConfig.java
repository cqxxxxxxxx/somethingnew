package com.cqx.config;

import com.cqx.Consumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BG307435 on 2017/9/13.
 */
@Configuration
public class MQConfig {

    public final static String queueName = "spring-boot";

    @Bean
    Queue queue() {
        /**
         *
         Caused by: com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - inequivalent arg 'x-dead-letter-routing-key'for queue 'buc_fin_withdraw' in vhost 'bsp_test': received the value 'admin.deadletter' of type 'longstr' but current is none, class-id=50, method-id=10)
         at com.rabbitmq.utility.ValueOrException.getValue(ValueOrException.java:67)
         at com.rabbitmq.utility.BlockingValueOrException.uninterruptibleGetValue(BlockingValueOrException.java:33)
         at com.rabbitmq.client.impl.AMQChannel$BlockingRpcContinuation.getReply(AMQChannel.java:361)
         at com.rabbitmq.client.impl.AMQChannel.privateRpc(AMQChannel.java:226)
         at com.rabbitmq.client.impl.AMQChannel.exnWrappingRpc(AMQChannel.java:118)
         ... 25 common frames omitted
         Caused by: com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=406, reply-text=PRECONDITION_FAILED - inequivalent arg 'x-dead-letter-routing-key'for queue 'buc_fin_withdraw' in vhost 'bsp_test': received the value 'admin.deadletter' of type 'longstr' but current is none, class-id=50, method-id=10)
         at com.rabbitmq.client.impl.ChannelN.asyncShutdown(ChannelN.java:484)
         at com.rabbitmq.client.impl.ChannelN.processAsync(ChannelN.java:321)
         at com.rabbitmq.client.impl.AMQChannel.handleCompleteInboundCommand(AMQChannel.java:144)
         at com.rabbitmq.client.impl.AMQChannel.handleFrame(AMQChannel.java:91)
         at com.rabbitmq.client.impl.AMQConnection$MainLoop.run(AMQConnection.java:554)
         ... 1 common frames omitted
         */
//      死信配置 1. exchange需要声明  2.queue的声明里需要指定死信的exchange 和 route key
        Map<String, Object> args = new HashMap<>();
//       x-dead-letter-exchange    声明  死信交换机
        args.put("x-dead-letter-exchange", "dlx-exchange-finance");
//       x-dead-letter-routing-key  声明 死信路由键 不写就是默认之前的
        args.put("x-dead-letter-routing-key", "admin.deadletter");
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public ConnectionFactory rabbitConnectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }


    /**
     * 通过方法参数注入(一般这种好理解)跟直接里面调用方法效果一样
     * https://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#beans-java
     * @param consumer
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(Consumer consumer) {
        return new MessageListenerAdapter(consumer, "receiveMessage");
//        return new MessageListenerAdapter(consumer(), "receiveMessage");
    }

    @Bean
    public Consumer consumer(){
        return new Consumer();
    }

}
