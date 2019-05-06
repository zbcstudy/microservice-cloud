package com.wondertek.springcloud.redisson.configuration;

import com.wondertek.springcloud.redisson.aop.LockAop;
import com.wondertek.springcloud.redisson.aop.MQAop;
import com.wondertek.springcloud.redisson.properties.RedissonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by win on 2019/5/6.
 */
@Configuration
@EnableConfigurationProperties(value = RedissonProperties.class)
@ConditionalOnClass(RedissonProperties.class)
public class RedissonConfiguration {

    @Autowired
    RedissonProperties redissonProperties;

    @Bean
    @ConditionalOnMissingBean(LockAop.class)
    public LockAop lockAop() {
        return new LockAop();
    }

    @Bean
    @ConditionalOnMissingBean(MQAop.class)
    public MQAop mqAop() {
        return new MQAop();
    }

}
