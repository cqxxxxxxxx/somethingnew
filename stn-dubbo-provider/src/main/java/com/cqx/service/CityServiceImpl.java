package com.cqx.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cqx.model.City;

/**
 * Created by BG307435 on 2017/9/26.
 */
@Service(version = "1.0.0")
public class CityServiceImpl implements CityService {
    @Override
    public City findCityByName(String cityName) {
        City city = new City();
        city.setName("dys 啊噗啊噗");
        return city;
    }
}
