package com.cqx.service;

import com.cqx.model.City;

/**
 * Created by BG307435 on 2017/9/26.
 */
public interface CityService {

    City findCityByName(String cityName);
}
