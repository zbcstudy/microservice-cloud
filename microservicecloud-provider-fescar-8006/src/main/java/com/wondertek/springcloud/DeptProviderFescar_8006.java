package com.wondertek.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import javax.sql.DataSource;

/**
 * 该项目用于测试分布式事务
 * @Author zbc
 * @Date 21:00-2019/1/23
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
//@EnableCircuitBreaker
public class DeptProviderFescar_8006 {

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DeptProviderFescar_8006.class);
    }

}
