package com.cqx;

import com.cqx.gen.ClazzContext;
import com.cqx.gen.GenContext;
import com.cqx.gen.excel.dao.CantonRepository;
import com.cqx.gen.excel.model.Canton;
import com.cqx.gen.excel.model.OrderHistoryAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by BG307435 on 2017/9/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GenTest {
    private List<Canton> provinceList = new ArrayList<>();
    private List<Canton> cityList = new ArrayList<>();
    private List<Canton> countryList = new ArrayList<>();

    @Autowired
    CantonRepository cantonRepository;

    @PostConstruct
    public void testFindAll() {
        provinceList = cantonRepository.findCantonsByLevel(1);
        cityList = cantonRepository.findCantonsByLevel(2);
        countryList = cantonRepository.findCantonsByLevel(3);
    }


    @Test
    public void testGen() throws InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        GenContext genContext = GenContext.Builder.newBuilder()
                .withPath("C:\\Users\\BG307435\\Desktop\\历史地址导入数据.xlsx")
                .withTableName("order_history_address")
                .withColumns("address_id, company_id, province, city, district, detail, operation_time,operator_id,province_id,city_id,district_id,contact_name, contact_phone")
                .withTargetClass(OrderHistoryAddress.class)
                .withEnhance(x -> {         //添加id
                    OrderHistoryAddress temp = (OrderHistoryAddress) x;
                    temp.setCompanyId(87);
                    temp.setProvinceId(getProvinceId(temp.getProvince()));
                    temp.setCityId(getCityId(temp.getCity()));
                    Integer districtId = getCountryId(temp.getDistrict());
                    temp.setDistrictId(getCountryId(temp.getDistrict()));
                    if (districtId == null) {
                        temp.setDetail(temp.getDistrict() + temp.getDetail());
                    }
                    temp.setOperationTime(new Date());
                    return temp;
                }).build();
        String sql = genContext.insertSQL();
        File file = new File("C:\\Users\\BG307435\\Desktop\\v1.5.8-prod-历史地址导入87.sql");
        if (file.exists()) {
            file.delete();
        }
        OutputStream out = new FileOutputStream(file);
        out.write(sql.getBytes());
    }

    private Integer getProvinceId(String name) {
        Optional<Canton> optional = provinceList.stream().filter(x -> x.getName().equals(name)).findAny();
        return optional.orElse(null).getId();
    }

    private Integer getCityId(String name) {
        Optional<Canton> optional = cityList.stream().filter(x -> x.getName().equals(name)).findAny();
        return optional.orElse(null).getId();
    }

    private Integer getCountryId(String name) {
        Optional<Canton> optional = countryList.stream().filter(x -> x.getName().equals(name)).findAny();
        Canton canton = optional.orElse(null);
        return canton == null ? null : canton.getId();
    }

}
