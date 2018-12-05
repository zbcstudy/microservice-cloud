package com.wondertek.springcloud.weather.job;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

//@EnableScheduling
//@Configuration
public class TestJob {

    @Scheduled(cron = "*/2 * * * * ?")
    public void run() {
        System.out.println("running");
    }
}
