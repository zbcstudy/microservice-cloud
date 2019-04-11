package com.wondertek.springcloud.elasticjob.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.wondertek.springcloud.elasticjob.annotation.ElasticJobConf;

import java.util.Arrays;
import java.util.List;

/**
 * Created by win on 2019/4/11.
 */
//@ElasticJobConf(name = "MyDataFlowJob")
public class MyDataFlowJob implements DataflowJob<String> {
    @Override
    public List<String> fetchData(ShardingContext shardingContext) {
        return Arrays.asList("1", "2", "3");
    }

    @Override
    public void processData(ShardingContext shardingContext, List<String> data) {
        System.out.println("处理数据：" + data.toString());
    }
}
