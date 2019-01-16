package com.wondertek.springcloud.rabbitmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PayOutputSink {

    @Output("outputBinding")
    MessageChannel afterLoadComplete();
}
