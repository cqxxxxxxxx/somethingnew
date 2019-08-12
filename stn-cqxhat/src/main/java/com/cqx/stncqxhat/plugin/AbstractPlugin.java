package com.cqx.stncqxhat.plugin;

import com.cqx.stncqxhat.support.keywords.KeyWordPool;

import javax.annotation.PostConstruct;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
public abstract class AbstractPlugin implements Plugin {

    protected KeyWordPool keyWordPool;

    @PostConstruct
    @Override
    public void initialize() {
        keyWordPool = KeyWordPool.of(this);
    }

    public int mode() {
        return this.metadata().getMode();
    }

    public int shiftL(int n) {
        return 1 << n;
    }
}
