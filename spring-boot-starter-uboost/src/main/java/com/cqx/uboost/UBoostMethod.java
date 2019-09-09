package com.cqx.uboost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/9
 */
public class UBoostMethod {
    private static final Logger log = LoggerFactory.getLogger(UBoostMethod.class);

    private Method method;

    private List<UBoostHandler> boostHandlers;

    private int handlerSize;

    public Object execute(Object originalObject, Object[] args) {
        args = before(args);
        Throwable t;
        try {
            Object result = method.invoke(originalObject, args);
            result = after(result, args);
            return result;
        } catch (IllegalAccessException e) {
            t = e;
        } catch (InvocationTargetException e) {
            t = e;
        }
        t = onException(t, args);
        log.error(t.getMessage(), t);
        throw new UBoostException(t);
    }

    private Object[] before(Object[] args) {
        for (int i = 0; i < handlerSize; i++) {
            args = boostHandlers.get(i).boostBefore(args);
        }
        return args;
    }

    private Object after(Object result, Object[] args) {
        for (int i = 0; i < handlerSize; i++) {
            result = boostHandlers.get(i).boostAfter(result, args);
        }
        return result;
    }

    private <T extends Throwable> T onException(T throwable, Object[] args) {
        for (int i = 0; i < handlerSize; i++) {
            throwable = boostHandlers.get(i).boostOnException(throwable, args);
        }
        return throwable;
    }
}
