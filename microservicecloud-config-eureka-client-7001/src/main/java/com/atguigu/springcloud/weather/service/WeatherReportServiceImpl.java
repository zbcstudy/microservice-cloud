package com.atguigu.springcloud.weather.service;

import com.atguigu.springcloud.weather.domain.Weather;
import com.atguigu.springcloud.weather.domain.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    public Weather getDataByCityId(String CityId) {
        WeatherResponse response = weatherDataService.getDataByCityId(CityId);
        Weather weather = response.getData();
        return weather;
    }
}
