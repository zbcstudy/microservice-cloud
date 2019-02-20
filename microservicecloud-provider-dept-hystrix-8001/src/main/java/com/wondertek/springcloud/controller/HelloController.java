package com.wondertek.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zbc
 * @Date 22:19-2019/2/20
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "error")
    public void dashboardTest() {
        int i = RandomUtils.nextInt(10);
        if (i < 3) {
            throw new RuntimeException("error");
        }
        System.out.println("hello word");
    }

    public void error() {
        System.out.println("hello world error");
    }
}
