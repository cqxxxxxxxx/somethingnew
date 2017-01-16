package com.cqx.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Shan on 2017/1/15.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{name}")
    public User getUser(@PathVariable("name") String name){
        return userService.selectByName(name);
    }

    @GetMapping("/{name}/test1")
    public User getUserTest1(@PathVariable("name") String name){
        return userService.selectByName1(name);
    }

    @DeleteMapping("/{name}")
    public void deleteUser(@PathVariable("name") String name){
        userService.deleteByName(name);
    }
}
