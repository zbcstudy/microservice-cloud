package com.wondertek.springcloud.config;

import org.mengyun.tcctransaction.interceptor.CompensableTransactionAspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @Author zbc
 * @Date 20:45-2019/1/25
 */
@Component
public class MyCompensableTransactionAspect extends CompensableTransactionAspect {
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
