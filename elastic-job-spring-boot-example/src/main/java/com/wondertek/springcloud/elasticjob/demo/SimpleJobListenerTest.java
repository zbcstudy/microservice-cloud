package com.wondertek.springcloud.elasticjob.demo;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by win on 2019/4/12.
 */
public class SimpleJobListenerTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-elasticJob.xml");
        ZookeeperRegistryCenter zookeeperRegistryCenter = (ZookeeperRegistryCenter) applicationContext.getBean("zookeeperRegCenter");
        System.out.println(zookeeperRegistryCenter);
        MySimpleJob mySimpleJob = (MySimpleJob) applicationContext.getBean("mySimpleJob");
        System.out.println(mySimpleJob);
    }
}
