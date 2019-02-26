package com.cqx;

import java.lang.instrument.Instrumentation;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/26
 */
public class InstrumentAgent {

    private static volatile Instrumentation globalInstrumentation;

    /**
     * 1. 指定 Premain-class: com.cqx.InstrumentAgent
     * 2. JVM启动时 调用main方法前会 执行这个类的premain方法，并且自动把参数注入进来
     * 3. 利用instrumentation来计算对象大小
     *
     * @param agentArgs
     * @param inst
     */
    public static void premain(final String agentArgs, final Instrumentation inst) {
        globalInstrumentation = inst;
    }

    public static long getObjectSize(final Object object) {
        if (globalInstrumentation == null) {
            throw new IllegalStateException("Agent not initialized.");
        }
        return globalInstrumentation.getObjectSize(object);
    }

}
