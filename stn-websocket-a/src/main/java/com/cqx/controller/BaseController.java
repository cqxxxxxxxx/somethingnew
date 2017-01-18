package com.cqx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shan on 2017/1/18.
 */
@Controller
@RequestMapping("/")
public class BaseController {

    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }
}
