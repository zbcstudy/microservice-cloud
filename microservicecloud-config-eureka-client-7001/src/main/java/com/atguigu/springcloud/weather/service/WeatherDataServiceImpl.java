package com.atguigu.springcloud.weather.service;

import com.atguigu.springcloud.weather.domain.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * 接口实现
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService{
    private static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?";

    @Autowired
    private RestTemplate restTemplate;
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
        WeatherResponse weatherResponse = null;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        String body = null;
        if (responseEntity.getStatusCodeValue() == 200) {
            body = responseEntity.getBody();
        }
        try {
            weatherResponse = mapper.readValue(body, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherResponse;
    }
}
