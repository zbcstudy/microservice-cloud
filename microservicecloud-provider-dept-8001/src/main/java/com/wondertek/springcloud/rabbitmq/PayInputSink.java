package com.wondertek.springcloud.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Author zbc
 * @Date 23:03-2019/1/15
 */
public interface PayInputSink {

    @Input("inputBinding")
    SubscribableChannel bindingOf1();
}
