package com.wondertek.springcloud;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;

import java.util.Collection;

/**
 * Created by win on 2019/4/23.
 */
public class CustomPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration();
        //逻辑库
        tableRuleConfiguration.setLogicTable("user");
        tableRuleConfiguration.setActualDataNodes("db${0..2}.t_user_${0..3}");
        //自定义分片算法实现
        StandardShardingStrategyConfiguration configuration = new StandardShardingStrategyConfiguration(
                "id", MyPreciseShardingAlgorithm.class.getName());

        //配置分库策略
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(configuration);

        //配置分表策略
        tableRuleConfiguration.setTableShardingStrategyConfig(configuration);

        return null;
    }
}
