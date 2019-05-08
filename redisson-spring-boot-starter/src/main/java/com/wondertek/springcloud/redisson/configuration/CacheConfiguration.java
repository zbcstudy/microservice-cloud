package com.wondertek.springcloud.redisson.configuration;

import com.wondertek.springcloud.redisson.annotation.EnableCache;
import com.wondertek.springcloud.redisson.properties.RedissonProperties;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by win on 2019/5/8.
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(RedissonProperties.class)
public class CacheConfiguration implements ImportAware {

    private static final Logger log = LoggerFactory.getLogger(CacheConfiguration.class);

    private String[] value;

    //缓存时间 默认30分钟
    private Long ttl;

    /**
     * 最长空闲时间 默认30分钟
     * @return
     */
    private long maxIdleTime;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    RedissonProperties redissonProperties;

    @Bean
    CacheManager cacheManager() {
        Map<String, CacheConfig> config = new HashMap<>();
        for (String s : value) {
            log.info("初始化spring cache空间{}", s);
            config.put(s, new CacheConfig(ttl, maxIdleTime));
        }
        return new RedissonSpringCacheManager(redissonClient, config);
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        Map<String, Object> annotationAttributes = importMetadata.getAnnotationAttributes(EnableCache.class.getName());
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(annotationAttributes);
        this.value = attributes.getStringArray("value");
        this.ttl = attributes.getNumber("ttl");
        this.maxIdleTime = attributes.getNumber("maxIdleTime");
    }
}
