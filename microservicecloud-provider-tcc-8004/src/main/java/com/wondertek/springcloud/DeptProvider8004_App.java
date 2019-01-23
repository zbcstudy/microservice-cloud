package com.wondertek.springcloud;

import org.mengyun.tcctransaction.TransactionRepository;
import org.mengyun.tcctransaction.spring.repository.SpringJdbcTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

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
@ImportResource("classpath:tcc-transaction.xml")
public class DeptProvider8004_App {

    @Autowired
    DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8004_App.class);
    }

    @Bean
    public TransactionRepository transactionRepository() {
        SpringJdbcTransactionRepository repository = new SpringJdbcTransactionRepository();
        repository.setDataSource(dataSource);
        return repository;
    }
}
