package com.cqx.stncqxhat.plugin;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
public abstract class AbstractPlugin implements Plugin {

    public int mode() {
        return this.metadata().getMode();
    }

    public int shiftL(int n) {
        return 1 << n;
    }
}
