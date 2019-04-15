package com.wondertek.springcloud;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig
public class ConfigApolloApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ConfigApolloApplication.class, args);
	}
}
