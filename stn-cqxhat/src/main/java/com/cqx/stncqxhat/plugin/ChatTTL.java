package com.cqx.stncqxhat.plugin;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.cqx.APT;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程上下文容器
 * 因为一个EventLoop对应多个channel，通过threadLocal来保存channel不是一个好的选择
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
@APT
@Deprecated
public class ChatTTL {

    /**
     * 阿里的库 可以支持异步执行时上下文传递的问题
     */
    private static TransmittableThreadLocal<Map<String, Object>> ttl = new TransmittableThreadLocal<Map<String, Object>>();

    public static void put(String key, Object o) {
        Map<String, Object> m = ttl.get();
        if (m == null) {
            m = new HashMap<>();
            ttl.set(m);
        }
        m.put(key, o);
    }

    public static <T> T get(String key) {
        Map<String, Object> m = ttl.get();
        return (T) m.get(key);
    }
}
