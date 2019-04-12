package com.wondertek.springcloud.elasticjob.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.wondertek.springcloud.elasticjob.core.MessageElasticJobListener;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by win on 2019/4/11.
 */
@Configuration
public class BeanConfig {

    /**
     * 任务执行事件数据源
     * @return
     */
    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.log")
    public DataSource dataSourceTwo() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public ElasticJobListener elasticJobListener() {
        return new MessageElasticJobListener();
    }
}
