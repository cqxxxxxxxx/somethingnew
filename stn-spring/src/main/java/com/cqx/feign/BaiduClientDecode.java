package com.cqx.feign;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/25
 */
public class BaiduClientDecode implements ErrorDecoder {
    public static final Random r = new Random();
//    @Override
//    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
//        if (r.nextBoolean()) {
//            throw new NoHystrixException("不经过hystrix拦截", new RuntimeException());
//        }
//        throw new RuntimeException();
////        return "访问成功";
//    }

    @Override
    public Exception decode(String methodKey, Response response) {
        throw new HystrixBadRequestException("不经过hystrix fallback");
//        return null;
    }
}
