package com.atguigu.springcloud.weather.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 集成定时任务
 */
public class WeatherDataSyncJob extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("weather quartz job start");
    }
}
