package com.cqx;

import com.alibaba.csp.sentinel.context.ContextUtil;

public class ContextUtilTest {

    public static void main(String[] args) {
        ContextUtil.enter("cqx", "web");
    }
}
