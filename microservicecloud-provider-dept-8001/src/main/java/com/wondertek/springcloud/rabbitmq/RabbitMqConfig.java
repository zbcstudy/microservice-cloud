package com.wondertek.springcloud.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zbc
 * @Date 21:50-2019/1/16
 */
@Configuration
public class RabbitMqConfig {

    public static final Logger log = LoggerFactory.getLogger(RabbitMqConfig.class);


    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue001",durable = "true"),
            exchange = @Exchange(value = "amq.direct",
                    durable = "true",
                    ignoreDeclarationExceptions = "true"),
            key = "test"
    ))
    public void receiveMessage(Message message) {
        byte[] body = message.getBody();
        System.out.println("消息体是：" + new String(body));
        log.info("get message: " + message);
    }
}
