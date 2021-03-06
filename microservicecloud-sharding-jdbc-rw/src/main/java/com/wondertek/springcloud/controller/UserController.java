package com.wondertek.springcloud.controller;

import com.wondertek.springcloud.domain.User;
import com.wondertek.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zbc
 * @Date 16:42-2019/3/3
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public String addUser() {
        User user = new User();
        user.setId(1l);
        user.setCity("上海");
        user.setName("赵必成");
        userService.addUser(user);
        return "success";
    }

    @RequestMapping("/list")
    public List<User> getAll() {
        return userService.getAll();
    }
}
