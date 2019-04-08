package com.wondertek.springcloud.elasticjob.autoconfigure;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.wondertek.springcloud.elasticjob.parse.JobConfParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by win on 2019/4/8.
 */
@Configuration
@EnableConfigurationProperties(ZookeeperProperties.class)
public class JobParserAutoConfiguration {

    @Autowired
    private ZookeeperProperties zookeeperProperties;

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter() {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(zookeeperProperties.getServerLists(),
                zookeeperProperties.getNamespace());
        zookeeperConfiguration.setBaseSleepTimeMilliseconds(zookeeperProperties.getBaseSleepTimeMilliseconds());
        zookeeperConfiguration.setConnectionTimeoutMilliseconds(zookeeperProperties.getConnectionTimeoutMilliseconds());
        zookeeperConfiguration.setDigest(zookeeperProperties.getDigest());
        zookeeperConfiguration.setMaxRetries(zookeeperProperties.getMaxRetries());
        zookeeperConfiguration.setMaxSleepTimeMilliseconds(zookeeperProperties.getMaxSleepTimeMilliseconds());
        zookeeperConfiguration.setSessionTimeoutMilliseconds(zookeeperProperties.getSessionTimeoutMilliseconds());
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }

    @Bean
    public JobConfParse jobConfParse() {
        return new JobConfParse();
    }
}

