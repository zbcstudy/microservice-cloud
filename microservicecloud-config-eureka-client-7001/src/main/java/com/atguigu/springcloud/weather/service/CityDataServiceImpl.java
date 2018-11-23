package com.atguigu.springcloud.weather.service;

import com.atguigu.springcloud.weather.domain.City;
import com.atguigu.springcloud.weather.domain.CityList;
import com.atguigu.springcloud.weather.util.XmlBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class CityDataServiceImpl implements CityDataService {

    @Override
    public List<City> listCity() {
        //读取xml文件
        Resource resource = new ClassPathResource("citylist.xml");
        BufferedReader bufferedReader = null;
        String readLine = "";
        StringBuffer buffer = new StringBuffer();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));

            while ((readLine = bufferedReader.readLine()) != null) {
                buffer.append(readLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //xml转换为对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class, buffer.toString());
        return cityList.getCityList();
    }
}
