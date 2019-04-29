package com.wondertek.springcloud.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Created by win on 2019/4/29.
 */
public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    public static final Logger log = LoggerFactory.getLogger(MyPropertyPlaceholderConfigurer.class);

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (propertyName.equalsIgnoreCase("test.content")) {
            log.info("当前即将过滤的内容是：" + propertyName + "=" + propertyValue);
            propertyValue = propertyValue + "新添加的内容";
        }else if (propertyName.equalsIgnoreCase("spring.datasource.password")) {
            propertyValue = propertyValue.replace("123", "456");
        }
        return super.convertProperty(propertyName, propertyValue);
    }
}
