package com.wondertek.springcloud.csp;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win on 2019/4/1.
 */
public class CspTest {

    public static void main(String[] args) {
        initFlowRules();
        while (true) {
            Entry entry = null;
            try {
                entry = SphU.entry("HelloWorld");
                //开始处理业务
                System.out.println("开始处理业务：helloWorld");

            } catch (BlockException e) {
                e.printStackTrace();
                System.out.println("block异常");
            }finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }
    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //进行限流，设置qps为20
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);

    }
}
