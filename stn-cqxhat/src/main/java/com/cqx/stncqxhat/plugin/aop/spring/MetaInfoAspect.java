package com.cqx.stncqxhat.plugin.aop.spring;

import com.cqx.Meta;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/10
 */
@Slf4j
@Aspect
@Component
public class MetaInfoAspect {

    @Around("execution(* com.cqx.stncqxhat.plugin..Plugin.metadata()))")
    public Object metaInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug(joinPoint.getSignature().toString());
        log.debug(joinPoint.getKind());
        log.debug(joinPoint.getArgs().toString());
        log.debug(joinPoint.getTarget().toString());
        log.debug(joinPoint.getClass().toString());
        Meta meta = joinPoint.getTarget().getClass().getAnnotation(Meta.class);
        Meta.Info info = new Meta.Info();
        info.setAuthor(meta.author());
        info.setMode(meta.mode());
        info.setPluginName(meta.pluginName());
        info.setVersion(meta.version());
        return info;
    }

}
