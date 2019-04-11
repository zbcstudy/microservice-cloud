elastic-job-spring-boot 使用注意事项
# elastic-job在整合curator时需要注意版本问题
    spring-cloud使用2.11.0的curator会报
    NoSuchMethodError: org.apache.curator.CuratorZookeeperClient.startAdvancedTracer
出现这个问题是，手动修改elastic-job-spring-boot-start的依赖包
```
    <dependency>
        <groupId>com.wondertek.springcloud</groupId>
        <artifactId>elastic-job-spring-boot-starter</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <exclusions>
            <exclusion>
                <groupId>org.apache.curator</groupId>
                <artifactId>curator-client</artifactId>
            </exclusion>
            <exclusion>
                <groupId>org.apache.curator</groupId>
                <artifactId>curator-framework</artifactId>
            </exclusion>
            <exclusion>
                <groupId>org.apache.curator</groupId>
                <artifactId>curator-recipes</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
```
添加新的依赖包
```
    <properties>
        <elastic-job.version>2.1.5</elastic-job.version>
        <curator.version>2.10.0</curator.version>
    </properties>
    
    <dependency>
        <groupId>org.apache.curator</groupId>
        <artifactId>curator-framework</artifactId>
        <version>${curator.version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.curator</groupId>
        <artifactId>curator-client</artifactId>
        <version>${curator.version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.curator</groupId>
        <artifactId>curator-recipes</artifactId>
        <version>${curator.version}</version>
        <exclusions>
            <exclusion>
                <artifactId>curator-framework</artifactId>
                <groupId>org.apache.curator</groupId>
            </exclusion>
        </exclusions>
    </dependency>
```

# 在使用@ElasticJobConf注解时,必须手动的添加属性 overwrite = true 才可以
    如果不添加客户端配置覆盖操作，任务在获取：
    String shardingParameter = shardingContext.getShardingParameter();
    时会无法获取