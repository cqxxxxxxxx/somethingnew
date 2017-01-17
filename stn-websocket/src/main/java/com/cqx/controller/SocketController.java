package com.cqx.controller;

import com.cqx.config.SocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;

/**
 * Created by Shan on 2017/1/16.
 */
@Controller
@RequestMapping("/")
public class SocketController {
    private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

    @Autowired
    SocketHandler socketHandler;

    @GetMapping("/login")
    public String toTest(HttpSession session){
        session.setAttribute("user", "cqx");
        return "test";
    }

    // 模拟服务端发送消息，其中可实现消息的广发或指定对象发送
    @GetMapping(value = "/message")
    public
    @ResponseBody
    String sendMessage(){
        double rand = Math.ceil(Math.random()*100);
        socketHandler.sendMessageToUser("cqx", new TextMessage("Websocket测试消息" + rand));
        return "message";
    }
}
