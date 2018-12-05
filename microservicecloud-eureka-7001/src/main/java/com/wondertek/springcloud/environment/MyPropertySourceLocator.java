package com.wondertek.springcloud.environment;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring-cloud配置BootStrap
 */
@Configuration
public class MyPropertySourceLocator implements PropertySourceLocator{
    @Override
    public PropertySource<?> locate(Environment environment) {
        if (environment instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment configurableEnvironment = ConfigurableEnvironment.class.cast(environment);
            //获取PropertySource
            MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
            //定义一个新的propertySource
            propertySources.addFirst(createPropertySource());
        }
        return null;
    }

    private PropertySource createPropertySource() {
        Map<String, Object> source = new HashMap<>();
        source.put("name", "赵必成-override");
        PropertySource propertySource = new MapPropertySource("over-my-property-source", source);
        return propertySource;
    }
}
