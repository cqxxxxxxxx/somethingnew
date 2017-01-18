package com.cqx.client;

import com.cqx.common.Message;
import com.cqx.common.UserPool;
import com.cqx.proxy.MessageProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by Shan on 2017/1/18.
 */
@Component
@ServerEndpoint("/websocket")
public class WebSocketClient {

    private static Logger LOG = LoggerFactory.getLogger(WebSocketClient.class);

    @OnOpen
    public void onOpen(Session session) {
        //加入用户池
        UserPool.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        LOG.info("user [" + session.getId() + "] Received: " + message);
        //给所有用户发送消息
        MessageProxy.getInstance().getProxy(Message.class).send(message, session);
    }

    @OnError
    public void onError(Throwable throwable) {
        LOG.error(throwable.getMessage());
    }

    @OnClose
    public void onClose(Session session) {
        //移除用户池
        UserPool.remove(session.getId());

    }
}
