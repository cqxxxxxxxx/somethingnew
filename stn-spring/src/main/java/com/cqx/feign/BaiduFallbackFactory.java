package com.cqx.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/25
 */
@Slf4j
@Component
public class BaiduFallbackFactory implements FallbackFactory<BaiduClient> {
    @Override
    public BaiduClient create(Throwable cause) {
        return new BaiduClient() {
            @Override
            public String index(String name, Object o) {
                log.error(cause.getMessage(), cause);
                log.error("hystrix fallback");
                return "default fallback";
            }

//            @Override
            public String index(String name) {
                log.error(cause.getMessage(), cause);
                log.error("hystrix fallback");
                return "default fallback";
            }
        };
    }
}
