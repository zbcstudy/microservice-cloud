package com.wondertek.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableDiscoveryClient// EurekaServer服务器端启动类,接受其它微服务注册进来
@EnableZipkinServer
public class ZipKinServerApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ZipKinServerApplication.class, args);
	}
}
