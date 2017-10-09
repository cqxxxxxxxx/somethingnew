package com.cqx.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cqx.model.City;
import org.springframework.stereotype.Component;

/**
 * Created by BG307435 on 2017/9/26.
 */
@Component
public class CityConsumer {

    @Reference(version = "1.0.0")
    CityService cityService;

    public City findCityByName(String cityName) {
        City city = cityService.findCityByName(cityName);
        System.out.println(city.toString());
        return city;
    }
}
