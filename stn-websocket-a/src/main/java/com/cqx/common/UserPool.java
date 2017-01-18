package com.cqx.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户的连接池，连接的加入，断开的退掉
 * Created by Shan on 2017/1/18.
 */
public class UserPool {
    private static Logger LOG = LoggerFactory.getLogger(UserPool.class);

    //用户池 吧websocket链接的用户放入这里
    private static Map<String, Object> USER_POOL = new HashMap<>();

    public static void add(Session session) {
        USER_POOL.put(session.getId(), session);
        LOG.info("user [" + session.getId() + "] connected");
    }

    public static void remove(String sessionId) {
        USER_POOL.remove(sessionId);
        LOG.info("user [" + sessionId + "] closed");
    }

    public static Session get(String sessionId) {
        return (Session) USER_POOL.get(sessionId);
    }

    public static Map<String, Object> getUserPool() {
        return USER_POOL;
    }

}
