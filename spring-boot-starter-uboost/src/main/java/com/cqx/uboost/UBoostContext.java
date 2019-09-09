package com.cqx.uboost;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/9
 */
public class UBoostContext {

    //每个UBoost注解增强的类或者方法对应一个chain
    public MultiValueMap<String, UBoostHandler> boostChain = new LinkedMultiValueMap();

    //所有的UBoostHandler
    public Map<String, UBoostHandler> boostHandlerMap = new ConcurrentHashMap<>();

    /**
     * 操作boostHandlerMap
     *
     * @param name
     * @param uBoostHandler
     */
    public void add(String name, UBoostHandler uBoostHandler) {
        boostHandlerMap.put(name, uBoostHandler);
    }

    /**
     * 操作boostHandlerMap
     *
     * @param name
     * @return
     */
    public UBoostHandler get(String name) {
        return boostHandlerMap.get(name);
    }

    /**
     * 操作boostChain
     *
     * @param name
     * @param uBoostHandlers
     */
    public void addChain(String name, UBoostHandler... uBoostHandlers) {
        Arrays.stream(uBoostHandlers)
                .forEach(x -> boostChain.add(name, x));
    }

    /**
     * 操作boostChain
     *
     * @param name
     * @return
     */
    public List<UBoostHandler> getChain(String name) {
        return boostChain.get(name);
    }

}
