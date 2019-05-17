package com.wondertek.springcloud.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author aaron.zhao 2019/5/17
 * @version 1.0
 */
public class Map8Test {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, "value" + i);
        }

        map.put("key11", null);
        map.forEach((key,value)->{
            System.out.println(key + "==" + value);
        });

        System.out.println(map.getOrDefault("key1", "key11"));
        System.out.println(map.getOrDefault("key11", "key11"));
        System.out.println(map.getOrDefault("key12", "defaultValue"));

        /**
         * putIfAbsent 首先去匹配key 如果key匹配不到 则增加一个key-value
         * 如果匹配到key 若原有的value不为null则不进行覆盖 返回原有的value
         *              若原有的value为null 则用新的value值进行覆盖,返回null
         */
        System.out.println(map.putIfAbsent("key1", "value111"));
        System.out.println(map.putIfAbsent("key12", "value122"));
        System.out.println(map.putIfAbsent("key11", "newValue"));
        System.out.println(map.get("key11"));

        System.out.println("=====================");
        /**
         * remove() 根据key进行匹配，如果value值也相同 则进行删除
         */
        System.out.println(map.remove("key1", "value11")); //删除失败 key value不匹配
        System.out.println(map.remove("key1", "value1"));   //删除成功
        map.forEach((k, v) -> System.out.println(k + "==" + v));
    }
}
