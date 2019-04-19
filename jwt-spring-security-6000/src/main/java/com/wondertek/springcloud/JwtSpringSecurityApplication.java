package com.wondertek.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtSpringSecurityApplication{

	public static final Logger log = LoggerFactory.getLogger(JwtSpringSecurityApplication.class);


	public static void main(String[] args)
	{
		SpringApplication.run(JwtSpringSecurityApplication.class, args);
	}

}
