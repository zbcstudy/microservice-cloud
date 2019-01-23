package com.wondertek.springcloud.controller;

import com.wondertek.springcloud.entities.User;
import com.wondertek.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zbc
 * @Date 21:38-2019/1/23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/save")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @RequestMapping("/confirm")
    public void confirmSaveUser(@RequestBody User user) {
        userService.confirmSaveUser(user);
    }

    @RequestMapping("/cancel")
    public void cancelSaveUser(@RequestBody User user) {
        userService.cancelSaveUser(user);
    }
}
