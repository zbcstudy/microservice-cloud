package com.wondertek.springcloud.elasticjob.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * 动态任务添加演示
 * Created by win on 2019/4/11.
 */
public class DynamicJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        //可以根据jobParameter来对不同的数据进行分类
        System.out.println(shardingContext.getJobParameter());
        System.out.println(shardingContext.getShardingParameter());
    }
}
