package com.atguigu.springcloud.weather.job;

import com.atguigu.springcloud.weather.domain.City;
import com.atguigu.springcloud.weather.service.CityDataService;
import com.atguigu.springcloud.weather.service.WeatherDataService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 集成定时任务
 */
@Configuration
public class WeatherDataSyncJob extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("weather quartz job start");
        //获取城市ID列表
        List<City> cityList = null;

        try {
            cityList = cityDataService.listCity();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        //遍历城市ID获取天气
        for (City city : cityList) {
            String cityId = city.getCityId();
            log.info("weather quartz job cityId： " + cityId);
            weatherDataService.syncDataByCityId(cityId);
        }

    }
}
