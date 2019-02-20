package com.cqx.stncqxhat.plugin;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.cqx.stncqxhat.support.apt.APT;

import java.util.Map;

/**
 * 线程上下文容器
 * TODO: 待处理 还是有问题的
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
@APT
public class ChatTTL {

    /**
     * 阿里的库 可以支持异步执行时上下文传递的问题
     */
    private static TransmittableThreadLocal<Map<String, Object>> ttl = new TransmittableThreadLocal<Map<String, Object>>();

    public static void put(String key, Object o) {
        Map<String, Object> m = ttl.get();
        m.put(key, o);
    }

    public static <T> T get(String key) {
        Map<String, Object> m = ttl.get();
        return (T) m.get(key);
    }
}
