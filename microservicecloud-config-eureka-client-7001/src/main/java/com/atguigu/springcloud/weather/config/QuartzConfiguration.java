package com.atguigu.springcloud.weather.config;

import com.atguigu.springcloud.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

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

//    @Bean
//    public JobDetailFactoryBean jobDetailFactoryBean() {
//        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
//        jobDetailFactoryBean.setJobClass(WeatherDataSyncJob.class);
//        jobDetailFactoryBean.setDurability(true);
//        return jobDetailFactoryBean;
//    }
//
//    @Bean
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
//        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
//        simpleTriggerFactoryBean.set
//
//    }

}
