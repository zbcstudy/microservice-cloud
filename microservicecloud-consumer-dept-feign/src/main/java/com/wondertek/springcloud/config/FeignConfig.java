package com.wondertek.springcloud.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置feign采用httpclient作为http连接方案
 * Created by win on 2019/4/10.
 */
@Configuration
public class FeignConfig {

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(400);
        connectionManager.setDefaultMaxPerRoute(200);
        return connectionManager;
    }

    @Bean
    public HttpClientBuilder httpClientBuilder(HttpClientConnectionManager connectionManager) {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(1500)
                .setConnectionRequestTimeout(1500).build();
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setConnectionManager(connectionManager);
        builder.setDefaultRequestConfig(requestConfig);
        builder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, false));
        return builder;
    }

    @Bean
    public HttpClient httpClient(HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }
}
