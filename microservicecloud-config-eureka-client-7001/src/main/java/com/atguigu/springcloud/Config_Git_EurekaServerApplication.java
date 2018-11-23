package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * EurekaServer服务器端启动类,接受其它微服务注册进来
 * 
 * @author zhouyang
 */
@SpringBootApplication
//@EnableEurekaServer
//@EnableScheduling
//@ImportResource(locations = "classpath:/spring-task.xml")
public class Config_Git_EurekaServerApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(Config_Git_EurekaServerApplication.class, args);
	}
}
