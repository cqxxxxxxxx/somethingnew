package com.cqx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Created by BG307435 on 2017/9/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SqlGenTest {
//    private static final String BSP_PATH = "C:\\Users\\BG307435\\Desktop\\test.xlsx";
//    private static final String BSP_PATH1 = "C:\\Users\\BG307435\\Desktop\\导入地址模板.xlsx";
//    private static final Logger logger = LoggerFactory.getLogger(SqlGenTest.class);
//    private List<Canton> provinceList = new ArrayList<>();
//    private List<Canton> cityList = new ArrayList<>();
//    private List<Canton> countryList = new ArrayList<>();
//
//
//
//    @Autowired
//    CantonRepository cantonRepository;
//
//    @PostConstruct
//    public void testFindAll(){
//        provinceList = cantonRepository.findCantonsByLevel(1);
//        cityList = cantonRepository.findCantonsByLevel(2);
//        countryList = cantonRepository.findCantonsByLevel(3);
//    }
//
//    @Test
//    public void testParseToId() throws IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
//        InsertGen insertGen = new InsertGen();
//        insertGen.init(OrderHistoryAddress.class);
//        insertGen.consumer(BSP_PATH1, "导入地址模版.xlsx");
//        List<OrderHistoryAddress> list = insertGen.exec();
//        List<OrderHistoryAddress> results =  list.stream().map(x -> {
//            x.setProvinceId(getProvinceId(x.getProvince()));
//            x.setCityId(getCityId(x.getCity()));
//            x.setDistrictId(getCountryId(x.getDistrict()));
//            return x;
//        }).collect(Collectors.toList());
//        insertGen.setTableName("order_history_address");
//        insertGen.setInsertColumns("address_id, company_id, province, city, district, detail, operation_time,operator_id,province_id,city_id,district_id,contact_name, contact_phone");
//        String a = insertGen.genFromList(results);
//        System.out.println(a);
//    }
//
//    @Test
//    public void testNew() {
//        InsertSQLGen gen = new InsertSQLGen();
//
//    }
//
//    private Integer getProvinceId(String name) {
//        Optional<Canton> optional = provinceList.stream().filter(x -> x.getName().equals(name)).findAny();
//        return optional.orElse(null).getId();
//    }
//
//    private Integer getCityId(String name) {
//        Optional<Canton> optional = cityList.stream().filter(x -> x.getName().equals(name)).findAny();
//        return optional.orElse(null).getId();
//    }
//
//    private Integer getCountryId(String name) {
//        Optional<Canton> optional = countryList.stream().filter(x -> x.getName().equals(name)).findAny();
//        Canton canton = optional.orElse(null);
//        return canton == null ? null : canton.getId();
//    }
//
//
//
//    @Test
//    public void sqlGenTest() throws IOException {
//        InsertGen insertGen = new InsertGen();
//        insertGen.consumer(BSP_PATH, "test.xlsx");
//        insertGen.setTableName("carrier");
//        insertGen.setInsertColumns("id,num,name,time_time");
//        String sql = insertGen.gen();
//        System.out.println(sql);
//    }
//
//    @Test
//    public void dateTest(){
//        Date date = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        System.out.println(dateFormat.format(date));
//        logger.error("货物重量【{}】错误，不能大于{}吨", 10, 20);
//    }



}
