package com.wondertek.springcloud.environment;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * BootStrap配置bean
 */
@Configuration
public class MyConfiguration implements ApplicationContextInitializer{
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        //获取PropertySource
        MutablePropertySources propertySources = environment.getPropertySources();
        //定义一个新的propertySource
        propertySources.addFirst(createPropertySource());

    }

    private PropertySource createPropertySource() {
        Map<String, Object> source = new HashMap<>();
        source.put("name", "赵必成");
        PropertySource propertySource = new MapPropertySource("my-property-source", source);
        return propertySource;
    }
}
