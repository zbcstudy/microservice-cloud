package com.wondertek.springcloud.elasticjob.demo;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.script.ScriptJob;
import com.wondertek.springcloud.elasticjob.annotation.ElasticJobConf;

/**
 * Created by win on 2019/4/11.
 */
//@ElasticJobConf(name = "MyScriptJob")
public class MyScriptJob implements ScriptJob {

    public void execute(ShardingContext context) {

    }
}
