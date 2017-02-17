package com.cqx.hv;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 *
 * Created by Shan on 2017/2/17.
 */
@Aspect
@Component
public class ValidAspect {

    @Autowired
    Validator validator;


    /**
     * 在hv包下所有类进行AOP
     */
    @Pointcut("execution(* com.cqx.hv.*.*(..))")
    public void validate() {
    }


    @Around(value = "validate()")
    private Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        DoValid doValid = AnnotationUtils.findAnnotation(method, DoValid.class);

        //如果方法上没有doValid注解，则继续执行方法
        if (doValid == null) {
            return pjp.proceed();
        }


        Annotation[][] paramAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < paramAnnotations.length; i++) {      //遍历方法参数上的注解
            Annotation[] annotations = paramAnnotations[i];
            Optional<Annotation> optional = Arrays.stream(annotations)
                    .filter(annotation -> annotation instanceof DoValid)
                    .findAny();
            if (!optional.isPresent()){ //如果这个参数上没有 doValid注解 则继续遍历下个参数
                continue;
            }
            Object arg = pjp.getArgs()[i];  //如果该参数上有 doValid注解 则获取该传入的实参
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(arg);
        }


    }
}
