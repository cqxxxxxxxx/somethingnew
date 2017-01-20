package com.cqx.proxy;

import com.cqx.annotation.Autowired;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  想错了 这个没用
 *  自动装配的过程应该是在bean实例化的时候进行的，而不是通过aop进行的。
 * Created by Shan on 2017/1/19.
 */
@Aspect
public class AutowireProcessor{

    private Logger logger = LoggerFactory.getLogger(AutowireProcessor.class);

    /**
     * 对demo包下所有类的所有方法并且有标记{@link Autowired}注解的进行织入
     */
    @Pointcut("execution(* com.cqx.model.*.*(..)) "
            + " && @annotation(com.cqx.annotation.Autowired)")
    public void doDependencyInjection(){}

    @Before("doDependencyInjection()")
    public void inject(JoinPoint joinPoint){
        System.out.println("autowired");
        System.out.println(joinPoint.getSignature().toString());
        System.out.println(joinPoint.getKind());
        System.out.println(joinPoint.getArgs().toString());
        System.out.println(joinPoint.getTarget().toString());
        System.out.println(joinPoint.getClass().toString());
    }
}
