package com.wondertek.springcloud.redisson.aop;

import com.wondertek.springcloud.redisson.annotation.MQPublish;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mq发送消息aop
 * Created by win on 2019/5/6.
 */
@Aspect
public class MQAop {

    public static final Logger log = LoggerFactory.getLogger(MQAop.class);

    @Autowired
    RedissonClient redissonClient;

    @Pointcut("@annotation(mqPublish)")
    public void MQAspect(MQPublish mqPublish) {
    }

    @Around("MQAspect(mqPublish)")
    public Object aroundMQAspect(ProceedingJoinPoint joinPoint, MQPublish mqPublish) {
        try {
            Object result = joinPoint.proceed();
            RTopic topic = redissonClient.getTopic(mqPublish.name());
            topic.publish(result);
            return result;
        } catch (Throwable throwable) {
            throw new RuntimeException("topic publish error");
        }
    }



}
