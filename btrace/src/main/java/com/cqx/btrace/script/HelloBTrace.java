package com.cqx.btrace.script;

import com.sun.btrace.AnyType;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.Strings.str;
import static com.sun.btrace.BTraceUtils.Strings.strcat;
import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.timeNanos;

/**
 * btrace 使用示例
 * http://calvin1978.blogcn.com/articles/btrace1.html
 */
@BTrace
public class HelloBTrace {
    /**
     * threadLocal变量 可以用来统计执行时间
     */
    @TLS private static long startTime = 0;

    /**
     * 新启动线程的时候
     */
    @OnMethod(clazz="java.lang.Thread", method="start")
    public static void onThreadStart() {
        println("thread start!");
    }

    /**
     * @param self     方法调用的接收者
     * @param name     方法入参，需要跟目标方法参数name和type绑定一致
     * @param result   返回值
     * @param duration 方法执行的时间 单位纳秒
     */
    @OnMethod(clazz = "com.cqx.btrace.controller.HelloController",
            method = "sayHi", location = @Location(Kind.RETURN))
    public static void sayHiReturn(@Self Object self, String name, @Return AnyType result, @Duration long duration) {
        println(self);
        println(name);
        println(result);
        println(duration/1000000);
        long time = timeNanos() - startTime;
        println(strcat("execute time(nanos): ", str(time)));
        println("ao li gei");
    }

    /**
     * 方法进入的时候
     */
    @OnMethod(clazz = "com.cqx.btrace.controller.HelloController",
            method = "sayHi", location = @Location(Kind.ENTRY))
    public static void sayHiEntry() {
        startTime = timeNanos();
        println("entry ooo");
    }

    /**
     * 特定行
     * 注意 如果行号设置不对，会失效，可以前后调整试一试
     * line=-1 则会每一行都执行，性能影响大
     */
    @OnMethod(clazz = "com.cqx.btrace.controller.HelloController",
            method = "sayHi", location = @Location(value = Kind.LINE, line = 15))
    public static void sayHiAtLine15() {
        println("line at 15 oooo");
    }
}
