package com.cqx.time;
import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;

/**
 * java8的新的时间API
 * Created by Shan on 2017/2/8.
 */
public class LocalDateDemo {

    @Test
    public void localDateTest(){

        //获取当前日期 不包括时分秒
        LocalDate today = LocalDate.now();

        System.out.println(today.toString());
        System.out.println(today.getYear() + "-" + today.getMonth().getValue() + "-" + today.getDayOfMonth());

        //Month 的加减
        Month thisMonth = today.getMonth();
        Month lastMonth = thisMonth.minus(1);
        System.out.println(lastMonth.getValue());

        //减少一个月后日期
        LocalDate date1 = today.minusMonths(1);
        System.out.println(date1.toString());

        // 获取当前月第一天和最后一天
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(firstDayOfMonth.toString());
        System.out.println(lastDayOfMonth.toString());

        //创建LocalTime 不包括日期
        LocalTime localTime = LocalTime.now();


        //创建LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();  //1
        LocalDateTime localDateTime1 = today.atTime(localTime); //2


        //创建Instant
        Instant instant = Instant.now();
        System.out.println(instant.toString());
        System.out.println(instant.toEpochMilli()); //unix时间戳 纳秒级别的

        //LocalDateTime 与 unix时间戳转换  -> 先转为 instant 然后获取long
        long unixTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();




    }
}
