package com.cqx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cqxxxxx on 2017/1/17.
 */
@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = "/sender", method = RequestMethod.GET)
    public String toSender(){
        return "sender";
    }

    @RequestMapping(value = "/receiver", method = RequestMethod.GET)
    public String toReceiver(){
        return "receiver";
    }

    @MessageMapping("/change-notice")
    public void greeting(String value){
        //使用这个方法进行消息转发
        this.simpMessagingTemplate.convertAndSend("/topic/notice", value);
    }

    /**
     * 同上 另一种写法
     * 接受 /app/change-notice 的消息，发给 topic/notice 客户端
     * @param value
     * @return
     */
    @MessageMapping("/change-notice1")
    @SendTo("/topic/notice")
    public String greeting1(String value){
        return value;
    }
}
