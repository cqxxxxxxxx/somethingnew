package com.cqx.skeleton.configurable.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/14
 */
public class UselessListener implements FoxListener {

    private static final Logger log = LoggerFactory.getLogger(UselessListener.class);

    /**
     * 触发顺序
     * 越小 优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    /**
     * 事件触发
     *
     * @param event
     */
    public void onEvent(FoxEvent event) {
        switch (event) {
            case START:
                log.info("useless listener on event start");
                break;
            case FINISH:
                log.info("useless listener on event finish");
                break;
            default:
                log.info("useless listener on event null");
                break;
        }
    }

    /**
     * 同步执行监听的逻辑
     *
     * @return
     */
    public boolean isAsync() {
        return false;
    }
}
