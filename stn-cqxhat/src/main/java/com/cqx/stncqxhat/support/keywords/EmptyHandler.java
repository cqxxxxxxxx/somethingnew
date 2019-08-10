package com.cqx.stncqxhat.support.keywords;

import com.cqx.stncqxhat.model.Message;
import com.sun.org.apache.regexp.internal.RE;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/10
 */
public class EmptyHandler implements KeyWordsHandler {

    public static EmptyHandler getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final EmptyHandler INSTANCE = new EmptyHandler();
    }

    @Override
    public void handle(Message message) {
        //do nothing
    }
}
