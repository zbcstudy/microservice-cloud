package com.wondertek.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by win on 2019/4/1.
 */
@RestController
@RequestMapping("/csp")
public class CspController {

    @SentinelResource(value = "get",blockHandler = "exceptionHandler")
    @RequestMapping("/get")
    public String get(@RequestParam(name = "id") String id) {
        System.out.println("获取到的参数是：" + id);
        return "www.baidu.com";
    }

    /**
     * 当限流组件生效之后，阻塞发生时，调用的方法
     */
    public String exceptionHandler(String id, BlockException e) {
        e.printStackTrace();
        return "错误发生在：" + id;
    }
}
