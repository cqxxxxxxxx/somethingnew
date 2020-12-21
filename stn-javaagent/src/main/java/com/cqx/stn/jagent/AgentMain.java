package com.cqx.stn.jagent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class AgentMain {

    public static void agentmain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException, InterruptedException {
        inst.addTransformer(new MyTransformer(), true);
        inst.retransformClasses(Class.forName("com.cqx.jagent.examples.Person"));
        System.out.println("Agent Main Done");
    }
}
