package com.cqx.hv;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

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
    @Pointcut("execution(* com.cqx.hv.demo.*.*(..))")
    public void validate() {
    }

    @Before(value = "validate()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("dobefore" + joinPoint.getKind());

        System.out.println(joinPoint.toString());
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

        JsonRes jsonRes = null;     //在循环外面声明引用，如果在里面每次new都会生成个新的引用
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
            jsonRes = validateArg(arg);
            if (jsonRes.getStatus() != 200){    //如果不是 200 则说明验证不通过
                throw new BussException(jsonRes.getMsg(), jsonRes.getStatus());

            }
        }

        return pjp.proceed();   //如果验证通过 则继续执行方法
    }


    /**
     * 验证传入的实参
     * 返回JsonRes
     * @param obj
     * @return
     */
    public JsonRes validateArg(Object obj){
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        if (constraintViolations.isEmpty()){
            return JsonRes.buildSuccess();
        }
        StringBuilder sb = new StringBuilder();
        constraintViolations.stream()
                .forEach(msg -> sb.append(msg.getMessage()).append(","));
        return new JsonRes(500, sb.toString());
    }
}
