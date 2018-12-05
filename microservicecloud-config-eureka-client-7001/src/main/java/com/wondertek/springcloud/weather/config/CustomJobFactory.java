package com.wondertek.springcloud.weather.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * 配置JobFactory解决quartz不能自动注入的问题
 */
public class CustomJobFactory extends SpringBeanJobFactory{

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);

        autowireCapableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
