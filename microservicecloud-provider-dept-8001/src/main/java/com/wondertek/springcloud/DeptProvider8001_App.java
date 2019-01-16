package com.wondertek.springcloud;

import com.wondertek.springcloud.rabbitmq.PayInputSink;
import com.wondertek.springcloud.rabbitmq.PayOutputSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@EnableDiscoveryClient //服务发现
@EnableBinding({PayOutputSink.class, PayInputSink.class})
public class DeptProvider8001_App implements CommandLineRunner{

	public static final Logger log = LoggerFactory.getLogger(DeptProvider8001_App.class);

	@Autowired
	PayOutputSink payOutputSink;

	public static void main(String[] args)
	{
		SpringApplication.run(DeptProvider8001_App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		payOutputSink.afterLoadComplete().send(MessageBuilder.withPayload("spring cloud test").build());
	}

	@StreamListener("inputBinding")
	public void getMessage(Message message) {
		Long startTime = System.currentTimeMillis();
		log.info("接收到死信队列的消息{}",message);
		Long endTime = System.currentTimeMillis();
		log.info("耗时{}", endTime-startTime);
	}
}
