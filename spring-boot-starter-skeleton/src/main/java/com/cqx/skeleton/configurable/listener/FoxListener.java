package com.cqx.skeleton.configurable.listener;

import org.springframework.core.Ordered;

import java.util.EventListener;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/14
 */
public interface FoxListener extends EventListener, Ordered, Async {

    /**
     * 触发顺序
     * 越小 优先级越高
     *
     * @return
     */
    @Override
    default int getOrder() {
        return 0;
    }

    /**
     * 事件触发
     *
     * @param event
     */
    default void onEvent(FoxEvent event) {
    }

    /**
     * 默认同步执行监听的逻辑
     *
     * @return
     */
    default boolean isAsync() {
        return false;
    }
}
