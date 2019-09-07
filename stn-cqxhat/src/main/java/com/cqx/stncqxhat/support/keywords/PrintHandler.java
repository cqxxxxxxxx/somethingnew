package com.cqx.stncqxhat.support.keywords;

import com.cqx.stncqxhat.model.Message;
import com.cqx.stncqxhat.support.core.ChannelContext;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/12
 */
public class PrintHandler implements KeyWordsHandler {

    public static PrintHandler getInstance() {
        return PrintHandler.LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final PrintHandler INSTANCE = new PrintHandler();
    }

    @Override
    public void handle(Message message) {
        ChannelContext.writeAndFlush(message);
    }
}
