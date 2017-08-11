package com.cqx.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * Created by BG307435 on 2017/8/11.
 */
public class OrderListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderListener.class);

    @Autowired
    private CountDownLatch countDownLatch;

    public OrderListener(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void onMessage(String message) {
        LOGGER.info("收到了:" + message);
        countDownLatch.countDown();
    }
}
