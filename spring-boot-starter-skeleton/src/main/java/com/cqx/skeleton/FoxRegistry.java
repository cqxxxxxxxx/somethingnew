package com.cqx.skeleton;

import com.cqx.skeleton.proxy.FoxProxyFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Fox的注册中心 管理着所以的代理类
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/10
 */
public class FoxRegistry {

    public static FoxRegistry singleton() {
        return Holder.SINGLETON;
    }

    private static class Holder {
        private static final FoxRegistry SINGLETON = new FoxRegistry();
    }

    private final Map<Class<?>, FoxProxyFactory<?>> knownMappers = new HashMap<Class<?>, FoxProxyFactory<?>>();

    private FoxRegistry() {
    }

    public <T> T getFox(Class<T> type, String name) {
        FoxProxyFactory<T> foxProxyFactory = (FoxProxyFactory<T>) knownMappers.get(type);
        return foxProxyFactory.newInstance(name);
    }

    public <T> boolean hasFox(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public <T> void addFox(Class<T> type) {
        if (type.isInterface()) {
            if (hasFox(type)) {
                throw new FoxException("Type " + type + " is already known to the FoxRegistry.");
            }
            boolean loadCompleted = false;
            try {
                knownMappers.put(type, new FoxProxyFactory<T>(type));
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    knownMappers.remove(type);
                }
            }
        }
    }

    public Collection<Class<?>> getFoxes() {
        return Collections.unmodifiableCollection(knownMappers.keySet());
    }

}
