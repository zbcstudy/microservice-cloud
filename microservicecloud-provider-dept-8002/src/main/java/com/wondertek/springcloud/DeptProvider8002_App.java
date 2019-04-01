package com.wondertek.springcloud;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient //本服务启动后会自动注册进eureka服务中
@EnableDiscoveryClient //服务发现
public class DeptProvider8002_App
{
	public static void main(String[] args) {
		//初始化sentinel配置
		initFlowRules();
		SpringApplication.run(DeptProvider8002_App.class, args);
	}

	private static void initFlowRules() {
		List<FlowRule> rules = new ArrayList<>();
		FlowRule rule = new FlowRule("get");
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		rule.setCount(20);
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}
}
