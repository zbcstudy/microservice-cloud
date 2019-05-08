package com.wondertek.springcloud.redisson.operation;

import com.wondertek.springcloud.redisson.properties.RedissonProperties;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by win on 2019/5/6.
 */
public class RedissonObject {

    @Resource
    RedissonClient redissonClient;

    @Resource
    RedissonProperties redissonProperties;

    /**
     * 获取存储的对象的值
     * @param name
     * @param <T>
     * @return
     */
    public <T> T getValue(String name) {
        return (T) getBucket(name).get();
    }

    /**
     * 获取对象空间
     * @param name
     * @param <T>
     * @return
     */
    public <T> RBucket<T> getBucket(String name) {
        return redissonClient.getBucket(name);
    }

    /**
     * 设置对象的值
     * @param name
     * @param value
     * @param <T>
     */
    public <T> void setValue(String name, T value) {
        setValue(name, value, redissonProperties.getDataValidTime());
    }

    /**
     * 设置对象的值
     * @param name
     * @param value
     * @param time 过期时间 -1 永久保存
     * @param <T>
     */
    public <T> void setValue(String name, T value, Long time) {
        RBucket<Object> bucket = redissonClient.getBucket(name);
        if (time == -1) {
            bucket.set(value);
        } else {
            bucket.set(value, redissonProperties.getDataValidTime(), TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 如果值已经存在则不设置
     * @param name
     * @param value
     * @param time
     * @param <T>
     * @return
     */
    public <T> Boolean trySetValue(String name, T value, Long time) {
        RBucket<Object> bucket = redissonClient.getBucket(name);
        if (time == -1) {
            return bucket.trySet(value);
        } else {
            return bucket.trySet(value, time, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 如果对象已经存在则不保存
     * @param name
     * @param value
     * @param <T>
     * @return
     */
    public <T> Boolean trySetValue(String name, T value) {
        return trySetValue(name, value, redissonProperties.getDataValidTime());
    }

    /**
     * 删除对象
     * @param name 键
     * @return
     */
    public Boolean delete(String name) {
        return redissonClient.getBucket(name).delete();
    }
}
