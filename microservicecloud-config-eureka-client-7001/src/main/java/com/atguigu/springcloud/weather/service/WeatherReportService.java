package com.atguigu.springcloud.weather.service;

import com.atguigu.springcloud.weather.domain.Weather;

public interface WeatherReportService {

    Weather getDataByCityId(String CityId);
}
