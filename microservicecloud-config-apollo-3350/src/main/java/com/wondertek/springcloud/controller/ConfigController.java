package com.wondertek.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Apollo配置中心是否可用
 * Created by win on 2019/4/12.
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    Environment environment;

    @RequestMapping("/get")
    public String getEnvironment() {
        return environment.getProperty("spring.application.name");
    }
}
