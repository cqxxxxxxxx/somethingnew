package com.cqx.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/25
 */
@FeignClient(name = "baidu", url = "www.baidu.com", fallbackFactory = BaiduFallbackFactory.class, configuration = BaiduFeignConfig.class)
public interface BaiduClient {

    @PostMapping("/")
    public String index(@RequestHeader("sb-header") String name, @RequestBody Object o);

}
