package com.wondertek.springcloud.redisson.annotation;

import com.wondertek.springcloud.redisson.enums.MQModel;

import java.lang.annotation.*;

/**
 * Created by win on 2019/5/6.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MQListener {

    /**
     * topic name
     * @return
     */
    String name();

    /**
     * 匹配模式
     * PRECISE精准的匹配 如:name="myTopic" 那么发送者的topic name也一定要等于myTopic  <br />
     * PATTERN模糊匹配 如: name="myTopic.*" 那么发送者的topic name 可以是 myTopic.name1 myTopic.name2.尾缀不限定
     */
    MQModel model() default MQModel.PRECISE;
}
