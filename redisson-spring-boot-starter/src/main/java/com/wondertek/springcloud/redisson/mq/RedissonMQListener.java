package com.wondertek.springcloud.redisson.mq;

import com.wondertek.springcloud.redisson.annotation.MQListener;
import org.redisson.api.RPatternTopic;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

/**
 * mq监听
 * Created by win on 2019/5/8.
 */
public class RedissonMQListener implements BeanPostProcessor {

    private static final Logger log = LoggerFactory.getLogger(RedissonMQListener.class);

    @Autowired
    RedissonClient redissonClient;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ReflectionUtils.doWithMethods(bean.getClass(), method -> {
            MQListener mqListener = AnnotationUtils.findAnnotation(method, MQListener.class);
            if (mqListener != null) {
                switch (mqListener.model()) {
                    case PRECISE:
                        RTopic rTopic = redissonClient.getTopic(mqListener.name());
                        log.info("注解Redisson精准监听器name={}", mqListener.name());
                        rTopic.addListener(Object.class, (channel, msg) -> {
                            try {
                                Object[] aras = new Object[method.getParameterTypes().length];
                                int index = 0;
                                for (Class<?> parameterType : method.getParameterTypes()) {
                                    String simpleName = parameterType.getSimpleName();
                                    if ("CharSequence".equals(simpleName)) {
                                        aras[index++] = channel;
                                    } else if (msg.getClass().getSimpleName().equals(simpleName) || "Object".equals(simpleName)) {
                                        aras[index++] = msg;
                                    } else {
                                        aras[index++] = null;
                                    }
                                }

                                method.invoke(bean, aras);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                        break;
                    case PATTERN:
                        RPatternTopic patternTopic = redissonClient.getPatternTopic(mqListener.name());
                        log.info("注解redisson模糊监听器name={}", mqListener.name());
                        patternTopic.addListener(Object.class, (pattern, channel, msg) -> {
                            try {
                                Object[] aras = new Object[method.getParameterTypes().length];
                                int index = 0;
                                boolean patternFlag = false;
                                for (Class<?> parameterType : method.getParameterTypes()) {
                                    String simpleName = parameterType.getSimpleName();
                                    if ("CharSequence".equals(simpleName)) {
                                        if (!patternFlag) {
                                            patternFlag = true;
                                            aras[index++] = pattern;
                                        } else {
                                            aras[index++] = channel;
                                        }
                                    } else if (msg.getClass().getSimpleName().equals(simpleName) || "Object".equals(simpleName)) {
                                        aras[index++] = msg;
                                    } else {
                                        aras[index++] = null;
                                    }
                                }
                                method.invoke(bean, aras);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                        break;
                }
            }
        },ReflectionUtils.USER_DECLARED_METHODS);
        return bean;
    }
}
