package com.wondertek.springcloud.elasticjob.core;

import com.dangdang.ddframe.job.executor.handler.JobExceptionHandler;
import com.wondertek.springcloud.elasticjob.util.DingDingMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义任务异常处理，有消息异常时发送钉钉通知
 * Created by win on 2019/4/11.
 */
public class CustomJobExceptionHandler implements JobExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(CustomJobExceptionHandler.class);

    @Override
    public void handleException(String jobName, Throwable cause) {
        log.error("job %s exception occur in job processing,the reason is: %s ", jobName, cause);
        DingDingMessageUtil.sendTextMessage("【"+jobName+"】任务异常。"+cause.getMessage());
    }
}
