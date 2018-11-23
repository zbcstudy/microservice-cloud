package com.atguigu.springcloud.weather.service;

import com.atguigu.springcloud.weather.domain.WeatherResponse;

/**
 * 天气数据接口
 */
public interface WeatherDataService {

    WeatherResponse getDataByCityId(String cityId);

    WeatherResponse getDataByCityName(String cityName);

    /**
     * 根据cityId同步数据
     * @param cityId
     */
    void syncDataByCityId(String cityId);
}
