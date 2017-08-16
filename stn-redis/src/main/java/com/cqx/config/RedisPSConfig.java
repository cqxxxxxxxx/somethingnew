package com.cqx.config;

import com.cqx.listener.OrderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * redis发布订阅监听器
 * Created by BG307435 on 2017/8/11.
 */
@Configuration
public class RedisPSConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisPSConfig.class);

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("order"));   //监听什么channel

        return container;
    }

    /**
     * 可以看下 MessageListenerAdapter的源码
     * 之所以能使用一个普通的POJO对象作为监听器是它里面做了个适配，通过反射调用pojo的方法。
     * <p>
     * Message listener adapter that delegates the handling of messages to target listener methods via reflection, with
     * flexible message type conversion. Allows listener methods to operate on message content types, completely independent
     * from the Redis API.
     *
     * @param listener
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(OrderListener listener) {
        return new MessageListenerAdapter(listener, "onMessage");   //监听到后执行的方法
    }

    @Bean
    OrderListener orderListener(CountDownLatch latch) {
        return new OrderListener(latch);
    }

    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

}
