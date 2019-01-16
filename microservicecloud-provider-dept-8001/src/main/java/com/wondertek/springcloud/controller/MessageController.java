package com.wondertek.springcloud.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zbc
 * @Date 21:59-2019/1/16
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        rabbitTemplate.convertAndSend("amq.direct","test",message);
        return "success";
    }
}
