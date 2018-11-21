package com.atguigu.springcloud.weather.service;

import com.atguigu.springcloud.weather.domain.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 接口实现
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService{

    private static final Logger log = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    private static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?";

    private static final long TIME_OUT = 10;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String url = WEATHER_URL+ "cityKey=" + cityId;

        return doGetWeather(url);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String url = WEATHER_URL+  "city=" + cityName;
        return doGetWeather(url);
    }

    private WeatherResponse doGetWeather(String url) {
        String key = url;
        String body = null;
        ObjectMapper mapper = new ObjectMapper();

        //先查询缓存，缓存中有数据，直返回
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        WeatherResponse weatherResponse = null;
        if (stringRedisTemplate.hasKey(key)) {
            body = ops.get(key);
            log.info("redis get value: " + body);
        } else {
            log.info("redis do not get key");
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            if (responseEntity.getStatusCodeValue() == 200) {
                body = responseEntity.getBody();
            }

            //将数据放入缓存中
            ops.set(url,body,TIME_OUT, TimeUnit.SECONDS);
        }

        try {
            weatherResponse = mapper.readValue(body, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }
}
