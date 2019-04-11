package com.wondertek.springcloud.elasticjob.core;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.wondertek.springcloud.elasticjob.util.DingDingMessageUtil;
import com.wondertek.springcloud.elasticjob.util.JsonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by win on 2019/4/11.
 */
public class MessageElasticJobListener implements ElasticJobListener {
    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String msg = date + " 【分布式任务-" + shardingContexts.getJobName() + "】任务开始执行====" + JsonUtils.toJson(shardingContexts);
        DingDingMessageUtil.sendTextMessage(msg);
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String msg = date + " 【分布式任务-" + shardingContexts.getJobName() + "】任务执行结束====" + JsonUtils.toJson(shardingContexts);
        DingDingMessageUtil.sendTextMessage(msg);
    }
}
