package com.wondertek.springcloud.redisson.annotation;

import com.wondertek.springcloud.redisson.configuration.MQConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by win on 2019/5/8.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MQConfiguration.class)
@Configuration
public @interface EnableMQ {
}
