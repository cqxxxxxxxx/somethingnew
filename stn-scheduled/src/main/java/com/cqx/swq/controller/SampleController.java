package com.cqx.swq.controller;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cqxxxxxxxx on 17-7-8.
 */
@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    Scheduler scheduler;


    @PostMapping
    public String addJob(@RequestParam String msg){


    }

}
