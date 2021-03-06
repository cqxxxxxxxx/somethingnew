package com.cqx.jagent.examples;


import com.cqx.stn.jagent.PremainAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * 试一下用Instrument来计算对象大小
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/26
 */
public class PremainAgentTest {

    public static void printObjectSize(Object object) {
        System.out.println("Object type: " + object.getClass() + ", size: " + PremainAgent.getObjectSize(object) + " bytes");
    }

    /**
     * JVM启动参数  -javaagent:"/Users/cqx/Projects/somethingnew/stn-javaagent/src/main/java/com/cqx/test/cqx-javaagent.jar"
     *
     * @param arguments
     */
    public static void main(String[] arguments) {
        Person person = new Person();
        person.sayHi();
    }


    private void printObjSize() {
        String emptyString = "";
        String string = "Estimating Object Size Using Instrumentation";
        String[] stringArray = {emptyString, string, "com.baeldung"};
        String[] anotherStringArray = new String[100];
        List<String> stringList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder(100);
        int maxIntPrimitive = Integer.MAX_VALUE;
        int minIntPrimitive = Integer.MIN_VALUE;
        Integer maxInteger = Integer.MAX_VALUE;
        Integer minInteger = Integer.MIN_VALUE;
        long zeroLong = 0L;
        double zeroDouble = 0.0;
        boolean falseBoolean = false;
        Object object = new Object();
        class EmptyClass {

        }
        EmptyClass emptyClass = new EmptyClass();
        class StringClass {
            public String s;

        }
        StringClass stringClass = new StringClass();
        printObjectSize(emptyString);
        printObjectSize(string);
        printObjectSize(stringArray);
        printObjectSize(anotherStringArray);
        printObjectSize(stringList);
        printObjectSize(stringBuilder);
        printObjectSize(maxIntPrimitive);
        printObjectSize(minIntPrimitive);
        printObjectSize(maxInteger);
        printObjectSize(minInteger);
        printObjectSize(zeroLong);
        printObjectSize(zeroDouble);
        printObjectSize(falseBoolean);
        printObjectSize(Day.TUESDAY);
        printObjectSize(object);
        printObjectSize(emptyClass);
        printObjectSize(stringClass);
    }
    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
