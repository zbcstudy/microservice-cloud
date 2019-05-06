package com.wondertek.springcloud.redisson.annotation;

import java.lang.annotation.*;

/**
 * Mq发送消息注解
 * Created by win on 2019/5/6.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MQPublish {
    /**
     * topic name
     * @return
     */
    String name();
}
