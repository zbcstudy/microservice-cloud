package com.wondertek.springcloud.weather.service;

import com.wondertek.springcloud.weather.domain.Weather;

public interface WeatherReportService {

    Weather getDataByCityId(String CityId);
}
