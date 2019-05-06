package com.wondertek.springcloud.redisson.enums;

/**
 * Created by win on 2019/5/6.
 */
public enum Model {
    //哨兵
    SENTINEL,
    //主从
    MASTERSLAVE,
    //单例
    SINGLE,
    //集群
    CLUSTER,
    //云托管
    REPLICATED
}
