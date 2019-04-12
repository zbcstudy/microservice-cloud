package com.wondertek.springcloud.elasticjob.core;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.wondertek.springcloud.elasticjob.util.DingDingMessageUtil;
import com.wondertek.springcloud.elasticjob.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by win on 2019/4/11.
 */
public class MessageElasticJobListener implements ElasticJobListener {

    private static final Logger log = LoggerFactory.getLogger(MessageElasticJobListener.class);

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String msg = date + " 【分布式任务-" + shardingContexts.getJobName() + "】任务开始执行====" + JsonUtils.toJson(shardingContexts);
        log.info(msg);
        DingDingMessageUtil.sendTextMessage(msg);
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String msg = date + " 【分布式任务-" + shardingContexts.getJobName() + "】任务执行结束====" + JsonUtils.toJson(shardingContexts);
        log.info(msg);
        DingDingMessageUtil.sendTextMessage(msg);
    }
}
