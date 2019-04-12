package com.wondertek.springcloud.elasticjob.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.wondertek.springcloud.elasticjob.annotation.ElasticJobConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by win on 2019/4/11.
 */
@ElasticJobConf(name = "MySimpleJob",cron = "0/10 * * * * ?",shardingItemParameters = "0=0,1=1",overwrite = true)
public class MySimpleJob implements SimpleJob {

    private static final Logger log = LoggerFactory.getLogger(MySimpleJob.class);

    @Override
    public void execute(ShardingContext shardingContext) {
//        System.out.println(2/0);
        String shardingParameter = shardingContext.getShardingParameter();
        log.info("【MySimpleJob】分片参数：" + shardingParameter);
        int value = Integer.parseInt(shardingParameter);
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == value) {
                String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
                System.out.println(time + ":开始执行简单任务" + i);
            }
        }
    }
}
