package com.wondertek.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
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

    public static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend("amq.direct","test",message);
        return "success";
    }


    RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData: " + correlationData);
            if (ack) {
                log.info("-----消息发送成功,correlationData: " + correlationData);
            }
        }
    };

    RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            log.info("return callBack: message" + message + " replyCode: " + replyCode);
        }
    };
}
