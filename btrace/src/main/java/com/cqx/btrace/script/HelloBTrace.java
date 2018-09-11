package com.cqx.btrace.script;

import com.sun.btrace.AnyType;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.Strings.str;
import static com.sun.btrace.BTraceUtils.Strings.strcat;
import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.timeNanos;

/**
 * btrace 使用示例
 */
@BTrace
public class HelloBTrace {

    @TLS private static long startTime = 0;

    @OnMethod(clazz="java.lang.Thread", method="start")
    public static void onThreadStart() {
        println("thread start!");
    }

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

    @OnMethod(clazz = "com.cqx.btrace.controller.HelloController",
            method = "sayHi", location = @Location(Kind.ENTRY))
    public static void sayHiEntry() {
        startTime = timeNanos();
        println("entry ooo");
    }


    @OnMethod(clazz = "com.cqx.btrace.controller.HelloController",
            method = "sayHi", location = @Location(value = Kind.LINE, line = 15))
    public static void sayHiAtLine15() {
        println("line at 15 oooo");
    }
}
