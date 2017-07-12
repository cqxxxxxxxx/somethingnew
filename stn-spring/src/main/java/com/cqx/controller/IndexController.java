package com.cqx.controller;

import com.cqx.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;



/**
 * Created by cqx on 2017/7/12.
 *
 * 1.关于post get中application/json 和 application/x-www-form-urlencoded 区别 参考[链接](https://imququ.com/post/four-ways-to-post-data-in-http.html)
 *
 */
@RestController
@RequestMapping("/")
public class IndexController {

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     [GET /get?queryString=%E5%A4%9A%E5%A4%9A HTTP/1.1
     Host: localhost:8080
     Connection: keep-alive
     User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36
     Cache-Control: no-cache
     Postman-Token: 894f73dc-6727-b717-6cf6-6c975d77feaa
     Content-Type: application/x-www-form-urlencoded
     Accept:
     Accept-Encoding: gzip, deflate, br
     Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4
     ]
     */
    @GetMapping("/get")
    public String getTest(@RequestParam String queryString) {
        return queryString;
    }


    /**
     *
     [POST /post?queryString=%E6%B5%8B%E8%AF%95 HTTP/1.1
     Host: localhost:8080
     Connection: keep-alive
     Content-Length: 30
     Postman-Token: 88401e37-3252-8e4f-e8f4-11bbd2ecff2b
     Cache-Control: no-cache
     Origin: chrome-extension://fhbjgbiflinjbdggehcddcbncdddomop
     User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36
     Content-Type: application/json               //消息主体是序列化后的 JSON 字符串
     Accept:
     Accept-Encoding: gzip, deflate, br
     Accept-Language: en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4
     {
        "name": "cqx",  //绑定到person中
        "age": 11
     }]
     */
    @PostMapping("/post")
    public String postTest(@RequestParam String queryString, @RequestBody Person person) {
        logger.info(queryString);
        logger.info(person.toString());
        return queryString;
    }


}
