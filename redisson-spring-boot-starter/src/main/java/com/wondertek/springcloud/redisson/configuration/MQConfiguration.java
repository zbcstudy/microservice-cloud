package com.wondertek.springcloud.redisson.configuration;

import com.wondertek.springcloud.redisson.mq.RedissonMQListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by win on 2019/5/8.
 */
@Configuration
public class MQConfiguration {

    @Bean
    @ConditionalOnMissingBean(RedissonMQListener.class)
    public RedissonMQListener redissonMQListener() {
        return new RedissonMQListener();
    }

}
