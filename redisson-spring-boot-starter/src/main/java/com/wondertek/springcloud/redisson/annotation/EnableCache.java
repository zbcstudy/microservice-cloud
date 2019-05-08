package com.wondertek.springcloud.redisson.annotation;

import com.wondertek.springcloud.redisson.configuration.CacheConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by win on 2019/5/8.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CacheConfiguration.class)
@Configuration
public @interface EnableCache {
    /**
     * 缓存的名称 @Cacheable,@CachePut,@CacheEvict的value必须包含在这里面
     * @return
     */
    String[] value();

    /**
     * 缓存时间 默认30分钟
     * @return
     */
    long ttl() default 1000*60* 30L;

    /**
     * 最长空闲时间 默认30分钟
     * @return
     */
    long maxIdleTime() default 1000*60* 30L;
}
