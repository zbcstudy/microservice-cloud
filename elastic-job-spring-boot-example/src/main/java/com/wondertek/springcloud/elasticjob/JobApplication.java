package com.wondertek.springcloud.elasticjob;

import com.wondertek.springcloud.elasticjob.annotation.EnableElasticJob;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.CountDownLatch;

/**
 * Created by win on 2019/4/11.
 */
@SpringBootApplication
@EnableElasticJob
@ComponentScan(basePackages = {"com.wondertek.springcloud.elasticjob"})
public class JobApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(JobApplication.class).web(true).run(args);

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
