package com.cqx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 *  @EnableWebSocket 开启websocket功能
 * Created by Shan on 2017/1/16.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

//    @Autowired
//    SocketHandler socketHandler;

    /**
     * 自定义的handler 用来处理websocket的相关东西
     * @return
     */
    @Bean
    public SocketHandler socketHandler(){
        return new SocketHandler();
    }

    /**
     * 该handler处理URL为 /myHandler的请求。
     * addInterceptor 是为了用该拦截器处理第一次websocket的Handshake,吧http请求的内容拦截添加到{@link WebSocketSession}中
     * setAllowedOrigins 是设置特定的域名可以跨域请求访问到。。应该是这样的=。=
     * @param registry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler(), "/socketServer")
                .addInterceptors(new HttpSessionHandshakeInterceptor()) //添加拦截器用于 handshake阶段 拦截request请求内容
                .setAllowedOrigins("http://baidu.com")    //跨域的设置有三种  default(不能跨域)   allowAllOrigins(允许任意的跨域请求)   String... origins(允许特定的域名跨域请求)
                .withSockJS();  //开启sockJS

    }


    /**
     * 在tomcat下
     * 配置websocket engine 的属性配置，比如message size，idle timeout之类的
     * @return
     */
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }
}
