package com.wondertek.springcloud.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zbc
 * @Date 21:50-2019/1/16
 */
@Configuration
public class RabbitMqConfig {

    public static final Logger log = LoggerFactory.getLogger(RabbitMqConfig.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        return rabbitTemplate;
    }

    RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
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
