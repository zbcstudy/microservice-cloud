package com.atguigu.springcloud.weather.config;

import com.atguigu.springcloud.weather.job.WeatherDataSyncJob;
import com.atguigu.springcloud.weather.service.CityDataService;
import com.atguigu.springcloud.weather.service.CityDataServiceImpl;
import com.atguigu.springcloud.weather.service.WeatherDataService;
import com.atguigu.springcloud.weather.service.WeatherDataServiceImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QuartzConfiguration {

//    /**
//     * JobDetail
//     * @return
//     */
//    @Bean
//    public JobDetail weatherDataSyncJobJobDetail() {
//        System.out.println("bean create");
//        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJobJobDetail")
//        .storeDurably().build();
//    }
//
//    /**
//     * trigger
//     */
//    @Bean
//    public Trigger weatherDataSyncJobTrigger() {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(2).repeatForever();
//        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobJobDetail()).withIdentity("weatherDataSyncJobTrigger")
//                .withSchedule(scheduleBuilder).build();
//    }
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean() {
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setTriggers(weatherDataSyncJobTrigger());
//        return schedulerFactoryBean;
//    }

    @Bean
    public CityDataService cityDataService() {
        return new CityDataServiceImpl();
    }

    @Bean
    public WeatherDataService weatherDataService() {
        return new WeatherDataServiceImpl();
    }

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(WeatherDataSyncJob.class);
        jobDetailFactoryBean.setDurability(true);
        JobDataMap map = new JobDataMap();
        map.put("cityDataService", cityDataService());
        map.put("weatherDataService", weatherDataService());
        jobDetailFactoryBean.setJobDataMap(map);
        return jobDetailFactoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        simpleTriggerFactoryBean.setRepeatInterval(30000);
        return simpleTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(simpleTriggerFactoryBean().getObject());
        schedulerFactoryBean.setJobFactory(customJobFactory());
        return schedulerFactoryBean;
    }

    @Bean
    public CustomJobFactory customJobFactory() {
        return new CustomJobFactory();
    }

}
