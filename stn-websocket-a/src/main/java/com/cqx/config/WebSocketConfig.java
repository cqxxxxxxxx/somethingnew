package com.cqx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by cqxxxxx on 2017/1/17.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //添加endpoint 用于接受客户端的连接  进行第一次握手 handshake
        registry.addEndpoint("/socket").withSockJS();   //withSockJS() 开启sockJS的支持
    }

    /**
     * broker：代理人 中间人
     * 设置消息连接请求的各种规范
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/topic");     //客户端的前缀
        registry.setApplicationDestinationPrefixes("/app"); //服务器接受websocket连接的前缀是app开头
    }

}
