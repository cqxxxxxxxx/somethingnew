package com.cqx.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 这个是使用spring-websocket 包来实现的websocket
 * 他是对原有的javax-websocket以及tomcat
 * webSocket 的处理类
 * 用来发送消息之类的
 * Created by Shan on 2017/1/16.
 */
public class SocketHandler implements WebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(SocketHandler.class);
    private static final ArrayList<WebSocketSession> users = new ArrayList<>();

    /**
     * webSocket连接建立后的处理
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("成功建立WebSocket连接");
        users.add(session);
        String username = session.getAttributes().get("user").toString();
        if (username != null)
            session.sendMessage(new TextMessage("已成功建立WebSocket连接恭喜发财"));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    /**
     * 连接出错的时候调用的方法
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        logger.error("连接出现错误:"+exception.toString());
        users.remove(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("WebSocket连接关闭");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    /**
     * 给所有用户发信息
     * @param message
     */
    public void sendMessageToUsers(TextMessage message){
        for (WebSocketSession user: users){
            try{
                if (user.isOpen())
                    user.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给特定用户发信息
     * @param username
     * @param message
     */
    public void sendMessageToUser(String username, TextMessage message){
        for (WebSocketSession user: users){
            if (user.getAttributes().get("user").equals(username)){
                if (user.isOpen()) {
                    try {
                        user.sendMessage(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }
}
